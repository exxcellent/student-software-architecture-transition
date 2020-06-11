import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginButtonComponent} from './ui-components/login-button/login-button.component';
import {SharedUiComponentsModule} from '../shared-ui-components';


@NgModule({
  declarations: [LoginButtonComponent],
  imports: [
    CommonModule,
    SharedUiComponentsModule,
  ],
  exports: [
    LoginButtonComponent
  ]
})
export class UserModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: UserModule,
      providers: [

      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: UserModule,
      providers: []
    }
  }

}
