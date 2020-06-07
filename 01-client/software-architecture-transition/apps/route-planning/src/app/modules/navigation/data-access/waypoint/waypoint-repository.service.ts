import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {Route} from '../../model/route';
import {WaypointConnectorService} from './connector/waypoint-connector.service';
import {Observable} from 'rxjs';
import {filter, flatMap, map} from 'rxjs/operators';
import {toISODateString} from '../../../shared/functions';
import {actions, RoutesState, selectors} from './state/route';
import {Waypoint} from '../../model/waypoint';
import {WaypointStatus} from '../../model/waypoint-status.enum';

@Injectable({
  providedIn: 'root'
})
export class WaypointRepositoryService {

  public routes$ = this.store.select(selectors.selectRoutes);
  public currentDay$ = this.store.select(selectors.selectCurrentDay);
  private currentDay: Date;

  constructor(private store: Store<RoutesState>, private connector: WaypointConnectorService) {
    this.currentDay$.subscribe(date => this.currentDay = date);
  }

  findRoute(date: Date, inspectorId: number): void{
    this.store.dispatch(actions.loadMyRoute());

    this.connector.findRoute(date, inspectorId).pipe(
      map((response: Route) => {
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

  myRouteOfDay$(date: Date): Observable<Route> {
    return this.routes$.pipe(
      map(routes => routes[toISODateString(date)]?.myRoute)
    )
  }

  get currentRoute$(): Observable<Route> {
    return this.myRouteOfDay$(this.currentDay);
  }

  get currentWaypoint$(): Observable<Waypoint> {
    return this.currentRoute$.pipe(
      map((route:Route) => route.waypoints),
      flatMap((waypoint) => waypoint),
      filter(waypoint => waypoint.status === WaypointStatus.ACTIVE),
    );
  }

  finishWaypoint(waypointId: number, version: number): void {
    console.log('Repository dispatch finishWaypoint');
    this.store.dispatch(actions.finishWaypoint({ waypointId: waypointId, version: version}))
  }

  cancelWaypoint(waypointId: number, version: number): void {
    this.store.dispatch(actions.cancelWaypoint({ waypointId: waypointId, version: version}))
  }

  notifyContact(waypointId: number, version: number): void {
    this.store.dispatch(actions.notifyContact({ waypointId: waypointId, version: version}))
  }
}
