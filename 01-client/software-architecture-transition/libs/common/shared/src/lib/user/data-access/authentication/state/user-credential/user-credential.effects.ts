import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {AuthenticationConnectorService} from '../../connector/authentication-connector.service';
import {actions} from './user-credential.actions';
import {catchError, concatMap, map} from 'rxjs/operators';
import {of} from 'rxjs';


@Injectable()
export class UserCredentialEffects {

  constructor(private actions$: Actions, private connector: AuthenticationConnectorService) {

  }

  loadUserCredentials$ = createEffect(() => {
    return this.actions$.pipe(

      ofType(actions.loadUserCredentials),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        this.connector.authenticate().pipe(
          map(data => actions.loadUserCredentialsSuccess({ data })),
          catchError(error => of(actions.loadUserCredentialsFailure({ error }))))
      )
    );
  });

}
