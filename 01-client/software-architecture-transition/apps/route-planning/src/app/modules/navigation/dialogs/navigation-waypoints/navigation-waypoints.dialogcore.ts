import {Injectable} from '@angular/core';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';
import {Observable} from 'rxjs';
import {Route} from '../../model/route';
import {Waypoint} from '../../model/waypoint';

@Injectable()
export class NavigationWaypointsDialogCore {

  private _currentRoute$: Observable<Route>;
  private _currentRoute: Route;

  constructor(private waypointRepository: WaypointRepositoryService) {
    this._currentRoute$ = waypointRepository.currentRoute$;

    this._currentRoute$.subscribe((route: Route) => {
      this._currentRoute = route;
    });
  }

  get waypoints(): Waypoint[] {
    const sortedWaypoints = Object.assign([], this._currentRoute.waypoints);
    return sortedWaypoints.sort((a, b) => a.orderIndex - b.orderIndex);
  }
}
