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
import {actions, RoutesState, selectors} from '@software-architecture-transition/data-access/navigation';
import {Store} from '@ngrx/store';
import {catchError, filter, switchMap, take, tap} from 'rxjs/operators';
import {ErrorCategory} from '@software-architecture-transition/shared';

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
        if (routes.error?.category) {
          console.error('Nav Guard: Detect connection error: ' + ErrorCategory[routes.error.category])
        }
      }),
      tap(routes => {
        if (!routes.loaded && !routes.loading && (!routes?.error || routes.error?.category === ErrorCategory.TECHNICAL)) {
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
