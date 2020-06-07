import {Injectable} from '@angular/core';
import {ConnectionError, DataProviderService, RestClient, UriBuilder} from '../../../../shared/data-access';
import {Route} from '../../../model/route';
import {RouteCTO} from '../types/route.cto';
import {
  fromNotificationResponse,
  fromResponse,
  fromUpdatedWaypointResponse,
  fromWaypointResponse,
  toNotificationRequest,
  toWaypointRequest
} from '../mapper/route.mapper';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {actions, AppState} from '../../../../../data-access/app/state/app';
import {Store} from '@ngrx/store';
import {Waypoint} from '../../../model/waypoint';
import {WaypointTO} from '../types/waypoint.to';
import {exists} from '../../../../shared/functions';
import {UpdatedWaypointsTO} from '../types/updated-waypoints.to';
import {Notification} from '../../../model/notification';
import {NotificationTO} from '../types/notification.to';

@Injectable({
  providedIn: 'root'
})
export class WaypointConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}';
  waypointsSubUrl = 'v1/waypoints/{waypointId}';
  notificationSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}/waypoints/{waypointId}/notifications';


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
    return this.http.PUT<WaypointTO>(this.getWaypointUrl(waypoint.waypointId),
      toWaypointRequest(waypoint)).pipe(
        map((response: WaypointTO) => {
          return fromWaypointResponse(response);
        }),
        catchError((error: ConnectionError) => {
          this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

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
          this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

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
          this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

          return throwError(error)
        }));
  }

  notifyContact(waypoint: Waypoint, notification: Notification): Observable<Notification> {
    return this.http.POST<NotificationTO>(this.getNotificationUrl(waypoint.date, waypoint.inspectorId, waypoint.waypointId),
      toNotificationRequest(waypoint, notification)).pipe(
      map((response: NotificationTO) => {
        return fromNotificationResponse(response);
      }),
      catchError((error: ConnectionError) => {
        this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

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

  private getNotificationUrl(date: Date, inspectorId: number, waypointId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = new UriBuilder()
      .fromPath(this.baseUrl)
      .path(this.notificationSubUrl).build();

    url = url.replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`);
    url = url.replace("{waypointId}", `${waypointId}`);

    return url;
  }

}
