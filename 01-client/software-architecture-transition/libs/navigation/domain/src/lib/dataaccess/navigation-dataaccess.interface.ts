import {Observable} from 'rxjs';
import {RouteDTO} from './route';
import {WaypointDTO} from './waypoint';

export interface NavigationDataAccess {

  findRoute(date: Date, inspectorId: number): void;

  nextDay(): void;

  previousDay(): void;

  myRouteOfDay$(date: Date): Observable<RouteDTO>;

  currentRoute$(): Observable<RouteDTO>;

  currentWaypoint$(): Observable<WaypointDTO>;

  finishWaypoint(waypointId: number, version: number): void;

  cancelWaypoint(waypointId: number, version: number): void;
}
