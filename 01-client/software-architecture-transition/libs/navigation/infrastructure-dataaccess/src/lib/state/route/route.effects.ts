import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, filter, flatMap, map, withLatestFrom} from 'rxjs/operators';
import {of} from 'rxjs';

import {actions} from './route.actions';
import {AuthenticationRepositoryService} from '@software-architecture-transition/shared';
import {WaypointConnectorService} from '../../connector/waypoint-connector.service';
import {RoutesState} from './route.reducer';
import {Store} from '@ngrx/store';
import {selectors} from './route.selectors';
import {WaypointDTO, WaypointStatusDTO} from '../../model';


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
          filter((wp: WaypointDTO) => wp.waypointId === waypointId && wp.version === version),
          map((waypoint: WaypointDTO) => {
            const clone: WaypointDTO = {
              ...waypoint,
              status: WaypointStatusDTO.FINISHED
            };

            return clone;
          }),
          concatMap((waypointWithState: WaypointDTO) => {

              return this.connector.finishWaypoint(waypointWithState).pipe(
                map((waypoint: WaypointDTO[]) => {
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
          filter((wp: WaypointDTO) => wp.waypointId === waypointId && wp.version === version),
          map((waypoint: WaypointDTO) => {
            const clone: WaypointDTO = {
              ...waypoint,
              status: WaypointStatusDTO.CANCELED
            };

            return clone;
          }),
          concatMap((waypointWithState: WaypointDTO) => {

            return this.connector.cancelWaypoint(waypointWithState).pipe(
              map((waypoint: WaypointDTO[]) => {
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
}
