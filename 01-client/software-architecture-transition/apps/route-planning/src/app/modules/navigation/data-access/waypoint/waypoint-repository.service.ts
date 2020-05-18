import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {Route} from '../../model/route';
import {WaypointConnectorService} from './connector/waypoint-connector.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {toISODateString} from '../../../shared/functions';
import {selectors} from './state/route/route.selectors';
import {actions} from './state/route/route.actions';
import {RoutesState} from './state/route/route.reducer';

@Injectable({
  providedIn: 'root'
})
export class WaypointRepositoryService {

  public routes$ = this.store.select(selectors.selectRoutes);

  constructor(private store: Store<RoutesState>, private connector: WaypointConnectorService) { }

  findRoute(date: Date, inspectorId: number): void{
    this.store.dispatch(actions.loadMyRoute());

    this.connector.findRoute(date, inspectorId).pipe(
      map((response: Route) => {
        this.store.dispatch(actions.loadMyRouteSuccess({data: response}));
        return response;
      })
    )
  }

  myRouteOfDay$(date: Date): Observable<Route> {
    return this.routes$.pipe(
      map(routes => routes[toISODateString(date)]?.myRoute)
    )
  }
}
