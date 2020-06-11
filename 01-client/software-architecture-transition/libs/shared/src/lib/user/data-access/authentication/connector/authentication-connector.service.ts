import {Injectable} from '@angular/core';
import {UserCredentialsTO} from '../types/user-credentials.to';
import {JwtTokenTO} from '../types/jwt-token.to';
import {UserCredentials} from '../../../model/user-credentials';
import {fromResponse, toRequest} from '../mapper/user-credentials.mapper';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Store} from '@ngrx/store';
import {RestClient} from '../../../../data-access/rest-client.class';
import {DataProviderService} from '../../../../data-access/data-provider.service';
import {ConnectionError} from '../../../../data-access/types/connection-error.class';
import {AppState} from '../../../../app/data-access/app/state/app/app.reducer';
import {appStateActions} from '../../../../app/data-access/app/state/app/app.actions';

@Injectable()
export class AuthenticationConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/authenticate';
  private _defaultUser: UserCredentials = { user: 'ADMIN', password: 'thesis', inspectorId: 1};

  constructor(private http: RestClient, private appStore: Store<AppState>) {
    super()
  }

  authenticate(userCredentials: UserCredentials = this._defaultUser): Observable<UserCredentials> {
    const request: UserCredentialsTO = toRequest(userCredentials);

    return this.http.POST<JwtTokenTO>(this.getUrl(), request).pipe(
      map((response: JwtTokenTO) => {

        const validUntil = new Date(Date.now());
        validUntil.setHours(validUntil.getHours() + 3);

        const userCredentialsResponse = fromResponse(request, response);
        userCredentialsResponse.validUntil = validUntil;
        userCredentialsResponse.inspectorId = userCredentials.inspectorId;

        return userCredentialsResponse;
      }),
      catchError((error: ConnectionError) => {
        console.error('Auth Connector: Dispatch show notification');
        this.appStore.dispatch(appStateActions.showNotification({data: error.asNotification }));

        return throwError(error);
      }));
  }

}
