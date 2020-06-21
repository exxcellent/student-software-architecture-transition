import {Injectable} from '@angular/core';
import {AuthenticationConnectorService} from './connector/authentication-connector.service';
import {UserCredentials} from '../../model/user-credentials';
import {UserCredentialState} from './state/user-credential/user-credential.reducer';
import {Store} from '@ngrx/store';
import {actions} from './state/user-credential/user-credential.actions';
import {selectors} from './state/user-credential/user-credential.selectors';
import {AuthenticationWebStorageService} from './webstorage/authentication-web-storage.service';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {exists} from '../../../functions';

@Injectable()
export class AuthenticationRepositoryService {

  public userCredentials$ = this.store.select(selectors.selectUserCredentials);
  public userCredentialsLoading$ = this.store.select(selectors.selectUserCredentialsLoading);
  public userAuthenticated$ = this.store.select(selectors.selectUserAuthenticated);

  private _userCredentials: UserCredentials;
  private _defaultUser: UserCredentials = { user: 'ADMIN', password: 'thesis', inspectorId: 1};


  constructor(private store: Store<UserCredentialState>,
              private connector: AuthenticationConnectorService,
              private webstorage: AuthenticationWebStorageService) {

    this.userCredentials$.subscribe((userCredentials) => {
      this._userCredentials = userCredentials;

      this.updateSessionStorage();
    });

    if (exists(this.webstorage.validUntil) && this.webstorage.validUntil.getTime() > Date.now()) {
      if (exists(this.webstorage.jwtToken) && exists(this.webstorage.userName)) {
        const userCredentialsFromWebStorage: UserCredentials = {
          user: this.webstorage.userName,
          password: null,
          jwtToken: this.webstorage.jwtToken,
          inspectorId: this.webstorage.inspectorId
        };

        this.store.dispatch(actions.loadUserCredentialsSuccess({data: userCredentialsFromWebStorage}))
      }
    } else {
      this.webstorage.clearAll();
    }
  }

  private updateSessionStorage() {
    // update session storage
    if (exists(this._userCredentials?.jwtToken)) {
      this.webstorage.jwtToken = this._userCredentials.jwtToken;
    }
    if (exists(this._userCredentials?.user)) {
      this.webstorage.userName = this._userCredentials.user;
    }
    if (exists(this._userCredentials?.validUntil)) {
      this.webstorage.validUntil = this._userCredentials.validUntil;
    }
    if (exists(this._userCredentials?.inspectorId)) {
      this.webstorage.inspectorId = this._userCredentials.inspectorId;
    }
  }

  public authenticate(userCredentials: UserCredentials = this._defaultUser): Observable<UserCredentials> {
     return this.connector.authenticate(userCredentials).pipe(
        map((response: UserCredentials) => {
          this.store.dispatch(actions.loadUserCredentialsSuccess({ data: response }));

          return response;
      }));
  }

  public get userCredentials(): UserCredentials {
    return this._userCredentials;
  }

  public get jwtToken(): string {
    return this._userCredentials?.jwtToken;
  }

  public get inspectorId(): number {
    return this._userCredentials?.inspectorId;
  }

  public get isAuthenticated(): boolean {
    return exists(this._userCredentials?.user)
      && exists(this._userCredentials?.jwtToken
      && this._userCredentials?.validUntil?.getTime() <= Date.now());
  }

  public get hasUserCredentials(): boolean {
    return exists(this._userCredentials?.user) && exists(this._userCredentials?.password);
  }
}
