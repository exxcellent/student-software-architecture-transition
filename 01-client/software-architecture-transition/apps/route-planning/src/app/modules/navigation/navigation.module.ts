import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TabBarComponent} from './ui-components';
import {NavigationPage} from './pages';
import {RouterModule} from '@angular/router';
import {NavigationMapComponent} from './dialogs/navigation-map/navigation-map.component';
import {NavigationWaypointsComponent} from './dialogs/navigation-waypoints/navigation-waypoints.component';
import {AgmCoreModule} from '@agm/core';
import {environment} from '@software-architecture-transition/environments';
import {NavigationDaySelectorComponent} from './dialogs/navigation-day-selector/navigation-day-selector.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {NavigationWaypointDetailsComponent} from './dialogs/navigation-waypoint-details/navigation-waypoint-details.component';
import {TranslateModule} from '@ngx-translate/core';
import {LocalDatePipe, SharedModule} from '@software-architecture-transition/shared';
import {NavigationDialogCoreModule, NavigationGuard} from '@software-architecture-transition/dialog-core';
import {SharedUiComponentsModule} from '@software-architecture-transition/shared-ui-components';


@NgModule({
  declarations: [
    NavigationPage,
    TabBarComponent,
    NavigationMapComponent,
    NavigationWaypointsComponent,
    NavigationDaySelectorComponent,
    NavigationWaypointDetailsComponent,
    LocalDatePipe,
  ],
  exports: [
    TabBarComponent
  ],
  imports: [
    CommonModule,
    TranslateModule,
    NavigationDialogCoreModule.forRoot(),
    RouterModule.forChild([
      {
        path: '', component: NavigationPage,
        canActivate: [NavigationGuard],
        canActivateChild: [NavigationGuard],
        canLoad: [NavigationGuard],
        children: [
          {path: 'map', component: NavigationMapComponent},
          {path: 'waypoints', component: NavigationWaypointsComponent},
          {path: 'details/:waypointId', component: NavigationWaypointDetailsComponent},
          {path: '', redirectTo: 'waypoints', pathMatch: 'full'},
        ],
      },
      // {
      //   path: 'navigation',
      //   component: TabBarComponent,
      //   outlet: "footer",
      //   pathMatch: 'prefix'
      // },
    ]),
    DragDropModule,
    AgmCoreModule.forRoot({
      // please get your own API key here:
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en
      apiKey: environment.googleMapsApiKey,
      libraries: ['geometry']
    }),
    SharedUiComponentsModule.forChild(),
    SharedModule.forChild()
  ]
})
export class NavigationModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: NavigationModule,
      providers: [

      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: NavigationModule,
      providers: []
    }
  }

}
