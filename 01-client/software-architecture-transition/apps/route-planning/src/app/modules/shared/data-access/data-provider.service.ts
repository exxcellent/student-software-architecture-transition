import {UriBuilder} from './uri-builder.class';
import {environment} from '../../../../environments/environment';

export abstract class DataProviderService {
  abstract serviceSubUrl: string;
  baseUrl = environment.backendBaseUrl;

  protected constructor() {
  }

  /**
   * return <BASE_URL>/<SERVICE_SUB_URL>
   */
  getUrl(): string {
    return new UriBuilder()
      .fromPath(this.baseUrl)
      .path(this.serviceSubUrl)
      .build();
  }
}
