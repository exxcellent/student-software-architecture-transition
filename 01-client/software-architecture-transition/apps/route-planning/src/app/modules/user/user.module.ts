import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import * as fromUserCredential from './data-access/authentication/state/user-credential/user-credential.reducer';
import {UserCredentialEffects} from './data-access/authentication/state/user-credential/user-credential.effects';
import {LoginButtonComponent} from './ui-components/login-button/login-button.component';
import {AuthenticationRepositoryService} from './data-access/authentication/authentication-repository.service';
import {AuthenticationConnectorService} from './data-access/authentication/connector/authentication-connector.service';
import {AuthenticationWebStorageService} from './data-access/authentication/webstorage/authentication-web-storage.service';
import {SharedUiComponentsModule} from '../shared-ui-components';


@NgModule({
  declarations: [LoginButtonComponent],
  imports: [
    CommonModule,
    StoreModule.forFeature(fromUserCredential.userCredentialFeatureKey, fromUserCredential.reducer),
    EffectsModule.forFeature([UserCredentialEffects]),
    SharedUiComponentsModule,
  ],
  exports: [
    LoginButtonComponent
  ]
})
export class UserModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: UserModule,
      providers: [
        AuthenticationRepositoryService,
        AuthenticationConnectorService,
        AuthenticationWebStorageService
      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: UserModule,
      providers: []
    }
  }

}
