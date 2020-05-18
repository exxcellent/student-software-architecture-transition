import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LIGHT_BLUE, SharedUiComponentsModule} from './modules/shared-ui-components';
import {HeaderComponent} from './layout';
import {FormsModule} from '@angular/forms';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {SharedModule} from './modules/shared/shared.module';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {JwtInterceptor} from './modules/shared/data-access/interceptors/jwt-interceptor.service';
import {UserModule} from './modules/user/user.module';
import * as fromApp from './state/app/app.reducer';

@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'navigation',
        loadChildren: () => import('./modules/navigation/navigation.module').then(m => m.NavigationModule)
      },
      {
        path: '',
        redirectTo: 'navigation',
        pathMatch: 'full'
      },
    ], {}), // TODO {initialNavigation: 'enabled', enableTracing: false }),

    UserModule.forRoot(),
    SharedUiComponentsModule.forRoot({useTheme: LIGHT_BLUE}),
    SharedModule.forRoot(),

    StoreModule.forRoot({}),
    StoreModule.forFeature(fromApp.appFeatureKey, fromApp.reducer),

    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    EffectsModule.forRoot([]),
  ],
  /* HTTP INTERCEPTORS */
  providers:    [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},

  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
