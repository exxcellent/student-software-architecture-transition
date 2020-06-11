import {Injectable} from '@angular/core';
import {today} from '@software-architecture-transition/shared';
import {WaypointRepositoryService} from '@software-architecture-transition/data-access/navigation';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavigationDaySelectorDiaglogCore {

  private _currentDay: Date = today();
  private _currentRouteDate$: Observable<Date>;
  private _currentRouteDate: Date;

  constructor(private waypointRepository: WaypointRepositoryService) {
    this._currentRouteDate$ = waypointRepository.currentDay$;

    this._currentRouteDate$.subscribe(date => this._currentRouteDate = date);
  }

  nextDay(): void {
    this.waypointRepository.nextDay();
  }

  previousDay(): void {
    this.waypointRepository.previousDay();
  }

  get currentDay(): Date {
    return this._currentDay;
  }
  get currentDay$(): Observable<Date> {
    return this._currentRouteDate$;
  }
}
