import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigationDialogCoreModule} from './modules/navigation';

@NgModule({
  imports: [
    CommonModule,
    NavigationDialogCoreModule.forChild()
  ],
})
export class DialogCoreModule {
  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: DialogCoreModule,
      providers: [

      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: DialogCoreModule,
      providers: []
    }
  }

}
