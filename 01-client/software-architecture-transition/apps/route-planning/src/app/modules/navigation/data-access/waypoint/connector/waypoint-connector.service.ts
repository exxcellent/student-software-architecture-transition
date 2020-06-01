import {Injectable} from '@angular/core';
import {ConnectionError, DataProviderService, RestClient, UriBuilder} from '../../../../shared/data-access';
import {Route} from '../../../model/route';
import {RouteCTO} from '../types/route.cto';
import {fromResponse, fromWaypointResponse, toWaypointRequest} from '../mapper/route.mapper';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {actions, AppState} from '../../../../../data-access/app/state/app';
import {Store} from '@ngrx/store';
import {Waypoint} from '../../../model/waypoint';
import {WaypointTO} from '../types/waypoint.to';

@Injectable({
  providedIn: 'root'
})
export class WaypointConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}';
  waypointsSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}/waypoints/{waypointId}';

  constructor(private http: RestClient, private appStore: Store<AppState>) {
    super()
  }

  findRoute(date: Date, inspectorId: number): Observable<Route>{

    return this.http.GET<RouteCTO>(this.getDailyWaypointUrl(date, inspectorId)).pipe(
      map((response: RouteCTO) => {
        return fromResponse(response);
    }),
      catchError((error: ConnectionError) => {
        this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

        return throwError(error)
      }));
  }

  updateWaypoint(waypoint: Waypoint): Observable<Waypoint>{
    const date = waypoint.date;
    const inspectorId = waypoint.inspectorId;

    return this.http.PUT<WaypointTO>(this.getWaypointUrl(date, inspectorId, waypoint.waypointId),
      toWaypointRequest(waypoint)).pipe(
        map((response: WaypointTO) => {
          return fromWaypointResponse(response);
        }),
        catchError((error: ConnectionError) => {
          this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

          return throwError(error)
        }));
  }

  private getDailyWaypointUrl(date: Date, inspectorId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = this.getUrl().replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`)

    return url;
  }

  private getWaypointUrl(date: Date, inspectorId: number, waypointId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = new UriBuilder()
      .fromPath(this.baseUrl)
      .path(this.waypointsSubUrl)
      .build();

    url = url.replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`)
    url = url.replace("{waypointId}", `${waypointId}`)

    return url;
  }

}
