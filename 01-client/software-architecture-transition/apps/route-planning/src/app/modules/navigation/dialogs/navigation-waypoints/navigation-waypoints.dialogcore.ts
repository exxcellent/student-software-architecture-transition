import {Injectable} from '@angular/core';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';
import {Observable} from 'rxjs';
import {Route} from '../../model/route';
import {Waypoint} from '../../model/waypoint';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {WAYPOINT_ICONS} from '../waypoint-icons';

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

  get waypoints(): WaypointWithIcon[] {
    const sortedWaypoints = Object.assign([], this._currentRoute.waypoints);
    sortedWaypoints.sort((a, b) => a.orderIndex - b.orderIndex);

    const waypointsWithIcon: WaypointWithIcon[] = [];
    sortedWaypoints.forEach((wp: Waypoint, index: number) => {
      waypointsWithIcon.push({ waypoint: wp, iconUrl: WAYPOINT_ICONS.appointmentIcons[index]})
    });

    return waypointsWithIcon;
  }
}
