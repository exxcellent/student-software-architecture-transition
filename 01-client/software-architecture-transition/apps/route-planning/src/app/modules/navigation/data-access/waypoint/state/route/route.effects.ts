import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, filter, flatMap, map, withLatestFrom} from 'rxjs/operators';
import {of} from 'rxjs';

import {actions} from './route.actions';
import {AuthenticationRepositoryService} from '../../../../../user/data-access/authentication/authentication-repository.service';
import {WaypointConnectorService} from '../../connector/waypoint-connector.service';
import {RoutesState} from './route.reducer';
import {Store} from '@ngrx/store';
import {selectors} from './route.selectors';
import {Waypoint} from '../../../../model/waypoint';
import {WaypointStatus} from '../../../../model/waypoint-status.enum';
import {Notification} from '../../../../model/notification';
import {NotificationChannel} from '../../../../model/notification-channel.enum';
import {today} from '../../../../../shared/functions';

@Injectable()
export class RouteEffects {

  constructor(private actions$: Actions,
              private connector: WaypointConnectorService,
              private session: AuthenticationRepositoryService,
              private store: Store<RoutesState>) {}

  loadMyRoutes$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(actions.loadMyRouteOfToday),
      withLatestFrom(this.store.select(selectors.selectCurrentDay)),
      map(([action, currentDate]) => {
        return currentDate;
      }),
      concatMap((currentDate: Date) =>
            this.connector.findRoute(currentDate, 1).pipe(
              map(data => {
                return actions.loadMyRouteSuccess({data: data})
              }),
              catchError(error => {
                return of(actions.loadMyRouteFailure({error}))
              })
          )
        )
      );
  });

  updateWaypointStateToFinished$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(actions.finishWaypoint),
      map(({waypointId, version}) => {
        return { waypointId: waypointId, version: version };
      }),
      concatMap(({waypointId, version}) =>

        this.store.select(selectors.selectCurrentWaypoints).pipe(
          flatMap(x => x),
          filter((wp: Waypoint) => wp.waypointId === waypointId && wp.version === version),
          map((waypoint: Waypoint) => {
            const clone: Waypoint = {
              ...waypoint,
              status: WaypointStatus.FINISHED
            };

            return clone;
          }),
          concatMap((waypointWithState: Waypoint) => {

              return this.connector.finishWaypoint(waypointWithState).pipe(
                map((waypoint: Waypoint[]) => {
                  return actions.updateWaypointSuccess({waypoint: waypoint[0], nextWaypoint: waypoint[1]})
                }),
                catchError(error => {
                  return of(actions.updateWaypointFailure({error}))
                })
              );
            })
        )
      )
    );
  });


  updateWaypointStateToCanceled$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(actions.cancelWaypoint),
      map(({waypointId, version}) => {
        return { waypointId: waypointId, version: version };
      }),
      concatMap(({waypointId, version}) =>

        this.store.select(selectors.selectCurrentWaypoints).pipe(
          flatMap(x => x),
          filter((wp: Waypoint) => wp.waypointId === waypointId && wp.version === version),
          map((waypoint: Waypoint) => {
            const clone: Waypoint = {
              ...waypoint,
              status: WaypointStatus.CANCELED
            };

            return clone;
          }),
          concatMap((waypointWithState: Waypoint) => {

            return this.connector.cancelWaypoint(waypointWithState).pipe(
              map((waypoint: Waypoint[]) => {
                return actions.updateWaypointSuccess({waypoint: waypoint[0], nextWaypoint: waypoint[1]})
              }),
              catchError(error => {
                return of(actions.updateWaypointFailure({error}))
              })
            );
          })
        )
      )
    );
  });


  notifyContact$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(actions.notifyContact),
      map(({waypointId, version}) => {
        return { waypointId: waypointId, version: version };
      }),
      concatMap(({waypointId, version}) =>
        this.store.select(selectors.selectCurrentWaypoints).pipe(
          flatMap(x => x),
          filter((wp: Waypoint) => wp.waypointId === waypointId && wp.version === version),
          map((waypoint: Waypoint) => {
            const notification: Notification = {
              waypointId: waypoint.waypointId,
              channel: NotificationChannel.AUTOMATIC,
              notifiedAt: today(),
              arrivalTimeInSeconds: 900
            };

            return { waypoint: waypoint, notification: notification };
          }),
          concatMap(({ waypoint, notification }) => {

            return this.connector.notifyContact(waypoint, notification).pipe(
              map((response: Notification) => {
                return actions.notifyContactSuccess({waypoint: waypoint, notification: response })
              }),
              catchError(error => {
                return of(actions.notifyContactFailure({error}))
              })
            );
          })
        )
      )
    );
  });
}
