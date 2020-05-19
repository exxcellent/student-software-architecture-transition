import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {Route} from '../../model/route';
import {WaypointConnectorService} from './connector/waypoint-connector.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {toISODateString} from '../../../shared/functions';
import {actions, RoutesState, selectors} from './state/route';

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
  }

  previousDay(): void {
    this.store.dispatch(actions.previousDay());
  }

  myRouteOfDay$(date: Date): Observable<Route> {
    return this.routes$.pipe(
      map(routes => routes[toISODateString(date)]?.myRoute)
    )
  }

  get currentRoute$(): Observable<Route> {
    return this.myRouteOfDay$(this.currentDay);
  }
}
