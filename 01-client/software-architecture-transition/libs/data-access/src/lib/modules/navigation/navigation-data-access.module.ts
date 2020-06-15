import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from '@ngrx/store';
import * as fromRoute from './state/route/route.reducer';
import {EffectsModule} from '@ngrx/effects';
import {RouteEffects} from './state/route';
import {SharedModule} from '@software-architecture-transition/shared';


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
