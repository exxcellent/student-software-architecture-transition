import {Injectable} from '@angular/core';
import {LocalDataProvider, LocalStorageDataProvider, SessionStorageDataProvider} from '../../../../shared/webstorage';

@Injectable()
export class AuthenticationWebStorageService extends LocalDataProvider{

  private USERNAME_KEY = 'route-planning.user.auth.username';
  private TOKEN_KEY = 'route-planning.user.auth.token';
  private VALID_UNTIL_KEY = 'route-planning.user.auth.valid';
  private INSPECTOR_ID_KEY = 'route-planning.user.auth.inspector';

  constructor(private session: SessionStorageDataProvider,
              private local: LocalStorageDataProvider) {
    super(local, session)
  }

  clearAll(): void {
    this.session.remove(this.USERNAME_KEY);
    this.session.remove(this.TOKEN_KEY);
    this.session.remove(this.VALID_UNTIL_KEY);
  }

  get userName(): string {
    return this.get(this.USERNAME_KEY);
  }

  set userName(userName: string) {
    this.setSessionScoped(this.USERNAME_KEY, userName);
  }

  get jwtToken(): string {
    return this.get(this.TOKEN_KEY);
  }

  set jwtToken(jwtToken: string) {
    this.setSessionScoped(this.TOKEN_KEY, jwtToken);
  }

  get validUntil(): Date {
    return new Date(Date.parse(this.get(this.VALID_UNTIL_KEY)));
  }

  set validUntil(validUntil: Date) {
    this.setSessionScoped(this.VALID_UNTIL_KEY, validUntil.toISOString());
  }

  get inspectorId(): number {
    return this.get(this.INSPECTOR_ID_KEY);
  }

  set inspectorId(inspectorId: number) {
    this.setSessionScoped(this.INSPECTOR_ID_KEY, `${inspectorId}`);
  }
}
