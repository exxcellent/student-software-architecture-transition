import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LIGHT_BLUE, SharedUiComponentsModule} from './modules/shared-ui-components';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    RouterModule.forRoot([

    ], { initialNavigation: 'enabled' }),
    SharedUiComponentsModule.forRoot({ useTheme: LIGHT_BLUE } )
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
