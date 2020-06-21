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
import {Store} from '@ngrx/store';
import {catchError, filter, switchMap, take, tap} from 'rxjs/operators';
import {actions, selectors, UserCredentialState} from '../user/data-access/authentication/state/user-credential';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(private router: Router, private store: Store<UserCredentialState>) {

  }

  private getFromStoreOrAPI(): Observable<any> {
    // return an Observable stream from the store
    return this.store.select(selectors.selectUserCredentialState).pipe(
      tap(userCredentials => {
        if (!userCredentials.loaded && !userCredentials.loading) {
          this.store.dispatch(actions.loadUserCredentials());
        }
      }),
      // filter unloaded
      filter(userCredentials => userCredentials.loaded),
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

    // return our Observable stream from above
    return this.getFromStoreOrAPI().pipe(
      // if it was successful, we can return Observable.of(true)
      switchMap(() => of(true)),
      // otherwise, something went wrong
      catchError(() => of(false))
    );
  }
  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return true;
  }
}
