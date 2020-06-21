import {InjectionToken, ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from '@ngrx/store';
import * as fromRoute from './state/route/route.reducer';
import {EffectsModule} from '@ngrx/effects';
import {RouteEffects} from './state/route';
import {SharedModule} from '@software-architecture-transition/shared';
import {NavigationDataAccess} from '@software-architecture-transition/dialog-core/navigation';
import {WaypointRepositoryService} from './waypoint-repository.service';

export let NAVIGATION_DATA_ACCESS = new InjectionToken<NavigationDataAccess>('NAVIGATION_DATA_ACCESS');

@NgModule({
  declarations: [
  ],
  exports: [
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature(fromRoute.routeFeatureKey, fromRoute.reducer),
    EffectsModule.forFeature([RouteEffects]),
    SharedModule.forChild()
  ]
})
export class NavigationDataAccessModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: NavigationDataAccessModule,
      providers: [
        {
          provide: NAVIGATION_DATA_ACCESS,
          useClass: WaypointRepositoryService
        }
      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: NavigationDataAccessModule,
      providers: []
    }
  }

}
