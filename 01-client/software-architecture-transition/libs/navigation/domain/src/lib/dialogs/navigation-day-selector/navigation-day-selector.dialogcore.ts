import {Inject, Injectable} from '@angular/core';
import {today} from '@software-architecture-transition/shared';
import {NAVIGATION_DATA_ACCESS} from '@software-architecture-transition/data-access/navigation';
import {Observable} from 'rxjs';
import {NavigationDataAccess} from '../../dataaccess';

@Injectable({
  providedIn: 'root'
})
export class NavigationDaySelectorDiaglogCore {

  private _currentDay: Date = today();
  private _currentRouteDate$: Observable<Date>;
  private _currentRouteDate: Date;

  constructor(@Inject(NAVIGATION_DATA_ACCESS) private navigationDataAccess: NavigationDataAccess) {
    this._currentRouteDate$ = navigationDataAccess.currentDay$;

    this._currentRouteDate$.subscribe(date => this._currentRouteDate = date);
  }

  nextDay(): void {
    this.navigationDataAccess.nextDay();
  }

  previousDay(): void {
    this.navigationDataAccess.previousDay();
  }

  get currentDay(): Date {
    return this._currentDay;
  }
  get currentDay$(): Observable<Date> {
    return this._currentRouteDate$;
  }
}
