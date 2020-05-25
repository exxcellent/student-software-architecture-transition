import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, map, withLatestFrom} from 'rxjs/operators';
import {of} from 'rxjs';

import {actions} from './route.actions';
import {AuthenticationRepositoryService} from '../../../../../user/data-access/authentication/authentication-repository.service';
import {WaypointConnectorService} from '../../connector/waypoint-connector.service';
import {RoutesState} from './route.reducer';
import {Store} from '@ngrx/store';
import {selectors} from './route.selectors';

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
}
