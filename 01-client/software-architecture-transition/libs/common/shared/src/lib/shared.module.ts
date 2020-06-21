import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {RestClient} from './data-access';
import {LocalStorageDataProvider, SessionStorageDataProvider} from './webstorage';
import {TruncatePipe} from './pipes';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {UserCredentialEffects} from './user/data-access/authentication/state/user-credential';
import * as fromUserCredential from './user/data-access/authentication/state/user-credential/user-credential.reducer';
import {
  AuthenticationConnectorService,
  AuthenticationRepositoryService,
  AuthenticationWebStorageService
} from './user/data-access/authentication';
import * as fromAppState from './app/data-access/app/state/app';

@NgModule({
  declarations: [
    TruncatePipe
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature(fromUserCredential.userCredentialFeatureKey, fromUserCredential.reducer),
    StoreModule.forFeature(fromAppState.appFeatureKey, fromAppState.appStateReducer),
    EffectsModule.forFeature([UserCredentialEffects]),
  ],
  exports: [
    HttpClientModule,
    TruncatePipe
  ]
})
export class SharedModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [
        RestClient,
        LocalStorageDataProvider,
        SessionStorageDataProvider,
        AuthenticationRepositoryService,
        AuthenticationConnectorService,
        AuthenticationWebStorageService
      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: []
    }
  }

}
