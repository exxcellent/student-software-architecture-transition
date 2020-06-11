import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {HeaderComponent} from './layout';
import {FormsModule} from '@angular/forms';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';
import {HTTP_INTERCEPTORS, HttpClient} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AuthGuard, JwtInterceptor, SharedModule} from '@software-architecture-transition/shared';
import {LIGHT_BLUE, SharedUiComponentsModule} from '@software-architecture-transition/shared-ui-components';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'navigation',
        loadChildren: () => import('./modules/navigation/navigation.module').then(m => m.NavigationModule),
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard]
      },
      {
        path: '',
        redirectTo: 'navigation',
        pathMatch: 'full'
      },
    ], {}), // TODO {initialNavigation: 'enabled', enableTracing: false }),

    SharedUiComponentsModule.forRoot({useTheme: LIGHT_BLUE}),
    SharedModule.forRoot(),

    StoreModule.forRoot({}),

    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    EffectsModule.forRoot([]),

    TranslateModule.forRoot({
      defaultLanguage: 'de',
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  /* HTTP INTERCEPTORS */
  providers:    [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},

  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
