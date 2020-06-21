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
import {catchError, switchMap, take} from 'rxjs/operators';
import {NavigationDataAccess} from '../dataaccess';

@Injectable()
export class NavigationGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(private router: Router,

              private navigationDataAccess: NavigationDataAccess) {

  }

  private getFromStoreOrAPI(): Observable<any> {
    // return an Observable stream from the store
    return this.navigationDataAccess.routesLoaded$().pipe(
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
