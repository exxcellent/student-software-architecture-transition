import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, map} from 'rxjs/operators';
import {EMPTY, of} from 'rxjs';

import {actions} from './user-credential.actions';


@Injectable()
export class UserCredentialEffects {

  loadUserCredentials$ = createEffect(() => {
    return this.actions$.pipe(

      ofType(actions.loadUserCredentials),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => actions.loadUserCredentialsSuccess({ data })),
          catchError(error => of(actions.loadUserCredentialsFailure({ error }))))
      )
    );
  });



  constructor(private actions$: Actions) {}

}
