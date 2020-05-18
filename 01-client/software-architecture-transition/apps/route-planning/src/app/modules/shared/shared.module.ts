import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {RestClient} from './data-access';
import {LocalStorageDataProvider} from './webstorage/data-sources/local-storage-data-provider.service';
import {SessionStorageDataProvider} from './webstorage/data-sources/session-storage-data-provider.service';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HttpClientModule
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
