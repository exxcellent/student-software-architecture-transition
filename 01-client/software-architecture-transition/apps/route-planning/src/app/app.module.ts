import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LIGHT_BLUE, SharedUiComponentsModule} from './modules/shared-ui-components';
import {HeaderComponent} from './layout';
import {NavigationModule} from './modules/navigation/navigation.module';

@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {
        path: 'navigation',
        loadChildren: () => import('./modules/navigation/navigation.module').then(m => m.NavigationModule),
      },
      {
        path: '',
        redirectTo: 'navigation',
        pathMatch: 'full'
      }
    ], {initialNavigation: 'enabled'}),
    SharedUiComponentsModule.forRoot({useTheme: LIGHT_BLUE}),
    NavigationModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
