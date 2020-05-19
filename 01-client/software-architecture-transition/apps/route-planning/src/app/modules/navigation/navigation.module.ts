import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TabBarComponent} from './ui-components';
import {NavigationPage} from './pages';
import {RouterModule} from '@angular/router';
import {NavigationMapComponent} from './dialogs/navigation-map/navigation-map.component';
import {NavigationWaypointsComponent} from './dialogs/navigation-waypoints/navigation-waypoints.component';
import {NavigationGuard} from './guards/navigation.guard';
import {AgmCoreModule} from '@agm/core';
import {environment} from '../../../environments/environment';
import {NavigationDaySelectorComponent} from './dialogs/navigation-day-selector/navigation-day-selector.component';
import {StoreModule} from '@ngrx/store';
import * as fromRoute from './data-access/waypoint/state/route/route.reducer';
import {EffectsModule} from '@ngrx/effects';
import {RouteEffects} from './data-access/waypoint/state/route';
import {SharedUiComponentsModule} from '../shared-ui-components';
import {SharedModule} from '../shared/shared.module';
import {DragDropModule} from '@angular/cdk/drag-drop';

@NgModule({
  declarations: [
    NavigationPage,
    TabBarComponent,
    NavigationMapComponent,
    NavigationWaypointsComponent,
    NavigationDaySelectorComponent,
  ],
  exports: [
    TabBarComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        path: '', component: NavigationPage,
        canActivate: [NavigationGuard],
        canActivateChild: [NavigationGuard],
        canLoad: [NavigationGuard],
        children: [
          {path: 'map', component: NavigationMapComponent},
          {path: 'waypoints', component: NavigationWaypointsComponent},
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
    StoreModule.forFeature(fromRoute.routeFeatureKey, fromRoute.reducer),
    EffectsModule.forFeature([RouteEffects]),
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
