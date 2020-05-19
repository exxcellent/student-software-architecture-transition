import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {RestClient} from './data-access';
import {LocalStorageDataProvider, SessionStorageDataProvider} from './webstorage';
import {TruncatePipe} from './pipes/truncate.pipe';

@NgModule({
  declarations: [
    TruncatePipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HttpClientModule,
    TruncatePipe
  ]
})
export class SharedModule {

  public static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [
        RestClient,
        LocalStorageDataProvider,
        SessionStorageDataProvider
      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: []
    }
  }

}
