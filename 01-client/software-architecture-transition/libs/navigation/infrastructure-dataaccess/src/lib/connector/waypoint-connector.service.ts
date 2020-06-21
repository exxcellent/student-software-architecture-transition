import {Injectable} from '@angular/core';
import {
  AppState,
  appStateActions,
  ConnectionError,
  DataProviderService,
  exists,
  RestClient,
  UriBuilder
} from '@software-architecture-transition/shared';
import {RouteCTO} from '../types/route.cto';
import {
  fromResponse,
  fromUpdatedWaypointResponse,
  fromWaypointResponse,
  toWaypointRequest
} from '../mapper/route.mapper';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Store} from '@ngrx/store';
import {WaypointTO} from '../types/waypoint.to';
import {UpdatedWaypointsTO} from '../types/updated-waypoints.to';
import {Route, Waypoint} from '../model';

@Injectable({
  providedIn: 'root'
})
export class WaypointConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}';
  waypointsSubUrl = 'v1/waypoints/{waypointId}';

  constructor(private http: RestClient, private appStore: Store<AppState>) {
    super()
  }

  findRoute(date: Date, inspectorId: number): Observable<Route>{

    return this.http.GET<RouteCTO>(this.getDailyWaypointUrl(date, inspectorId)).pipe(
      map((response: RouteCTO) => {
        return fromResponse(response);
    }),
      catchError((error: ConnectionError) => {
        this.appStore.dispatch(appStateActions.showNotification({data: error.asNotification }));

        return throwError(error)
      }));
  }

  updateWaypoint(waypoint: Waypoint): Observable<Waypoint>{
    return this.http.PUT<WaypointTO>(this.getWaypointUrl(waypoint.waypointId),
      toWaypointRequest(waypoint)).pipe(
        map((response: WaypointTO) => {
          return fromWaypointResponse(response);
        }),
        catchError((error: ConnectionError) => {
          this.appStore.dispatch(appStateActions.showNotification({data: error.asNotification }));

          return throwError(error)
        }));
  }

  finishWaypoint(waypoint: Waypoint): Observable<Waypoint[]>{
    return this.http.PUT<UpdatedWaypointsTO>(this.getWaypointUrl(waypoint.waypointId, 'finish'),
      toWaypointRequest(waypoint)).pipe(
        map((response: UpdatedWaypointsTO) => {
          return fromUpdatedWaypointResponse(response);
        }),
        catchError((error: ConnectionError) => {
          this.appStore.dispatch(appStateActions.showNotification({data: error.asNotification }));

          return throwError(error)
        }));
  }

  cancelWaypoint(waypoint: Waypoint): Observable<Waypoint[]>{
    return this.http.PUT<UpdatedWaypointsTO>(this.getWaypointUrl(waypoint.waypointId, 'cancel'),
      toWaypointRequest(waypoint)).pipe(
        map((response: UpdatedWaypointsTO) => {
          return fromUpdatedWaypointResponse(response);
        }),
        catchError((error: ConnectionError) => {
          this.appStore.dispatch(appStateActions.showNotification({data: error.asNotification }));

          return throwError(error)
        }));
  }

  private getDailyWaypointUrl(date: Date, inspectorId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = this.getUrl().replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`);

    return url;
  }

  private getWaypointUrl(waypointId: number, action?: string): string {
    let uriBuilder = new UriBuilder()
      .fromPath(this.baseUrl)
      .path(this.waypointsSubUrl);

    if (exists(action)) {
      uriBuilder.path(action);
    }

    let url = uriBuilder.build();
    url = url.replace("{waypointId}", `${waypointId}`);

    return url;
  }

}
