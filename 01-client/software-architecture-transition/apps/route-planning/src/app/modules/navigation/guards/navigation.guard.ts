import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from '@angular/router';
import {Observable, of} from 'rxjs';
import {actions, RoutesState, selectors} from '../data-access/waypoint/state/route';
import {Store} from '@ngrx/store';
import {catchError, filter, switchMap, take, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NavigationGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(private router: Router, private store: Store<RoutesState>) {

  }

  private getFromStoreOrAPI(): Observable<any> {
    // return an Observable stream from the store
    return this.store.select(selectors.selectRoutesState).pipe(
      tap(routes => {
        if (!routes.loaded && !routes.loading) {
          this.store.dispatch(actions.loadMyRouteOfToday());
        }
      }),
      // filter unloaded
      filter(routes => routes.loaded),
      take(1)
    );
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    // return our Observable stream from above
    return this.getFromStoreOrAPI().pipe(
      // if it was successful, we can return Observable.of(true)
      switchMap(() => of(true)),
      // otherwise, something went wrong
      catchError(() => of(false))
    );

    // log('State: '+ state.url);
    //
    // if (state.url.indexOf('footer:') === -1 &&  state.url.indexOf('mode=mobile') > -1) {
    //   log('Add footer outlet to route');
    //   let targetPath = 'navigation';
    //
    //   this.router.navigate([
    //       {
    //         outlets: {
    //           primary: [ 'navigation' ],
    //           footer: ['navigation']
    //         }
    //       }
    //     ],
    //     { queryParams: { mode: 'mobile'}, queryParamsHandling: 'merge' });
    // }
  }
  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return true;
  }
  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return true;
  }
}
