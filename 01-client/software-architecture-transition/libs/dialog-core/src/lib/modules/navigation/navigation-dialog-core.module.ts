import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from '@ngrx/store';
import * as fromRoute from './data-access/waypoint/state/route/route.reducer';
import {EffectsModule} from '@ngrx/effects';
import {RouteEffects} from './data-access/waypoint/state/route';
import {TranslateModule} from '@ngx-translate/core';
import {SharedModule} from '@software-architecture-transition/shared';


@NgModule({
  declarations: [
  ],
  exports: [
  ],
  imports: [
    CommonModule,
    TranslateModule,
    StoreModule.forFeature(fromRoute.routeFeatureKey, fromRoute.reducer),
    EffectsModule.forFeature([RouteEffects]),
    SharedModule.forChild()
  ]
})
export class NavigationDialogCoreModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: NavigationDialogCoreModule,
      providers: [

      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: NavigationDialogCoreModule,
      providers: []
    }
  }

}
