import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TabBarComponent} from './ui-components';
import {NavigationPage} from './pages';
import {RouterModule} from '@angular/router';
import {NavigationMapComponent} from './dialogs/navigation-map/navigation-map.component';
import {NavigationWaypointsComponent} from './dialogs/navigation-waypoints/navigation-waypoints.component';
import {NavigationGuard} from './guards/navigation.guard';


@NgModule({
  declarations: [
    NavigationPage,
    TabBarComponent,
    NavigationMapComponent,
    NavigationWaypointsComponent
  ],
  exports: [
    TabBarComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: 'navigation', component: NavigationPage,
        canActivate: [NavigationGuard],
        canActivateChild: [NavigationGuard],
        canLoad: [NavigationGuard],
        children: [
          { path: 'map', component: NavigationMapComponent },
          { path: 'waypoints', component: NavigationWaypointsComponent },
          { path: '', redirectTo: 'map', pathMatch: 'full'}
        ],
      },
      {
        path: 'navigation',
        component: TabBarComponent,
        outlet: "footer",
        pathMatch: 'prefix'
      },
    ])
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
