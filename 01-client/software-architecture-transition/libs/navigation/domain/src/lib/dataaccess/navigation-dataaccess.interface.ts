import {Observable} from 'rxjs';
import {RouteDTO} from './route';
import {WaypointDTO} from './waypoint';

export interface NavigationDataAccess {

  routes$: Observable<{ [key: string]: { myRoute: RouteDTO} }>;
  currentDay$: Observable<Date>;

  findRoute(date: Date, inspectorId: number): void;

  nextDay(): void;

  previousDay(): void;

  myRouteOfDay$(date: Date): Observable<RouteDTO>;

  currentRoute$(): Observable<RouteDTO>;

  currentWaypoint$(): Observable<WaypointDTO>;

  finishWaypoint(waypointId: number, version: number): void;

  cancelWaypoint(waypointId: number, version: number): void;

  routesLoaded$(): Observable<boolean>;
}
