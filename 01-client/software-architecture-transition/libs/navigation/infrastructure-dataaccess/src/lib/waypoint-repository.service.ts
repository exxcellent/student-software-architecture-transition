import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {WaypointConnectorService} from './connector/waypoint-connector.service';
import {Observable} from 'rxjs';
import {filter, flatMap, map, tap} from 'rxjs/operators';
import {ErrorCategory, toISODateString} from '@software-architecture-transition/shared';
import {actions, RoutesState, selectors} from './state/route';
import {NavigationDataAccess, RouteDTO, WaypointDTO, WaypointStatusDTO} from '../../../domain/src/lib/dataaccess';

@Injectable({
  providedIn: 'root'
})
export class WaypointRepositoryService implements NavigationDataAccess{

  public routes$ = this.store.select(selectors.selectRoutes);
  public currentDay$ = this.store.select(selectors.selectCurrentDay);
  private currentDay: Date;

  constructor(private store: Store<RoutesState>, private connector: WaypointConnectorService) {
    this.currentDay$.subscribe(date => this.currentDay = date);
  }

  findRoute(date: Date, inspectorId: number): void{
    this.store.dispatch(actions.loadMyRoute());

    this.connector.findRoute(date, inspectorId).pipe(
      map((response: RouteDTO) => {
        this.store.dispatch(actions.loadMyRouteSuccess({data: response}));
        return response;
      })
    )
  }

  nextDay(): void {
    this.store.dispatch(actions.nextDay());
    this.store.dispatch(actions.loadMyRouteOfToday())
  }

  previousDay(): void {
    this.store.dispatch(actions.previousDay());
    this.store.dispatch(actions.loadMyRouteOfToday())
  }

  myRouteOfDay$(date: Date): Observable<RouteDTO> {
    return this.routes$.pipe(
      map(routes => routes[toISODateString(date)]?.myRoute)
    )
  }

  currentRoute$(): Observable<RouteDTO> {
    return this.myRouteOfDay$(this.currentDay);
  }

  currentWaypoint$(): Observable<WaypointDTO> {
    return this.currentRoute$().pipe(
      map((route:RouteDTO) => route.waypoints),
      flatMap((waypoint) => waypoint),
      filter(waypoint => waypoint.status === WaypointStatusDTO.ACTIVE),
    );
  }

  finishWaypoint(waypointId: number, version: number): void {
    console.log('Repository dispatch finishWaypoint');
    this.store.dispatch(actions.finishWaypoint({ waypointId: waypointId, version: version}))
  }

  cancelWaypoint(waypointId: number, version: number): void {
    this.store.dispatch(actions.cancelWaypoint({ waypointId: waypointId, version: version}))
  }

  routesLoaded$(): Observable<boolean> {
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
      map(() => true));
    }
}
