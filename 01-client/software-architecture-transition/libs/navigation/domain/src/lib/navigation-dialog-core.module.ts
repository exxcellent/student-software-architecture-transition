import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {SharedModule} from '@software-architecture-transition/shared';
import {
  NAVIGATION_DATA_ACCESS,
  NavigationDataAccessModule
} from '@software-architecture-transition/data-access/navigation';
import {NavigationGuard} from './guards/navigation.guard';
import {NavigationDataAccess} from './dataaccess';
import {ActivatedRoute, Router} from '@angular/router';
import {TabBarDialogCore} from './ui-components/tab-bar/tab-bar.dialogcore';


export function getNavigationGuard(router: Router, navigationDataAccess: NavigationDataAccess) {
  return new NavigationGuard(router, navigationDataAccess);
}
export function getTabBarDialogCore(route: ActivatedRoute, navigationDataAccess: NavigationDataAccess) {
  return new TabBarDialogCore(route, navigationDataAccess);
}

@NgModule({
  declarations: [
  ],
  exports: [
  ],
  imports: [
    CommonModule,
    TranslateModule,
    SharedModule.forChild(),
    NavigationDataAccessModule.forRoot()
  ]
})
export class NavigationDialogCoreModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: NavigationDialogCoreModule,
      providers: [
        {
          provide: NavigationGuard,
          useFactory: getNavigationGuard,
          deps: [Router,  NAVIGATION_DATA_ACCESS ]
        },
        {
          provide: TabBarDialogCore,
          useFactory: getTabBarDialogCore,
          deps: [ActivatedRoute,  NAVIGATION_DATA_ACCESS ]
        }
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
