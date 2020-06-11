import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {SharedModule} from '@software-architecture-transition/shared';
import {NavigationDataAccessModule} from '@software-architecture-transition/data-access/navigation';


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
