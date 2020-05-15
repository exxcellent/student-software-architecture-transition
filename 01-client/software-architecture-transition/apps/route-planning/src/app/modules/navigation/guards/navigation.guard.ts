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
import {Observable} from 'rxjs';
import {log} from '../../shared/functions';

@Injectable({
  providedIn: 'root'
})
export class NavigationGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(private router: Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    log('State: '+ state.url);

    if (state.url.indexOf('footer:') === -1 &&  state.url.indexOf('mode=mobile') > -1) {
      log('Add footer outlet to route');
      let targetPath = 'navigation';

      this.router.navigate([
          {
            outlets: {
              primary: [ 'navigation' ],
              footer: ['navigation']
            }
          }
        ],
        { queryParams: { mode: 'mobile'}, queryParamsHandling: 'merge' });
    }

    return true;
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
