import {LocalStorageDataProvider} from './data-sources/local-storage-data-provider.service';
import {SessionStorageDataProvider} from './data-sources/session-storage-data-provider.service';
import {isNullOrUndefined, log} from '../functions';

/**
 * I provider data from the local user client and abstract the data source.
 *
 * The data sources can be
 * - session storage
 * - local storage
 */
export abstract class LocalDataProvider {

  constructor(
    private localStorage: LocalStorageDataProvider,
    private sessionStorage: SessionStorageDataProvider) {
  }

  /**
   * Persists key-value pair in session storage.
   *
   * Data will be lost after closing the browser tab.
   */
  protected setSessionScoped(key: string, value: string): boolean {
    if (this.sessionStorage.isAvailable()) {
      this.sessionStorage.set(key, value);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Persists key-value pair in local storage.
   *
   * Data remain in browser (similar to cookies)
   */
  protected setPermanently(key: string, value: string): boolean {
    if (this.localStorage.isAvailable()) {
      this.localStorage.set(key, value);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns a key-value pair from all local data sources.
   *
   * 1. try session storage
   * 2. try local storage
   */
  protected get(key: string): any {
    let value = null;
    if (this.sessionStorage.isAvailable()) {
      value = this.sessionStorage.get(key);
    }

    if (isNullOrUndefined(value) && this.localStorage.isAvailable()) {
      value = this.localStorage.get(key);
    }

    return value;
  }

  /**
   * Removes a key-value pair from all local data sources
   */
  protected remove(key: string): void {
    if (this.sessionStorage.isAvailable()) {
      this.sessionStorage.remove(key);
    }

    if (this.localStorage.isAvailable()) {
      this.localStorage.remove(key);
    }
  }

  /**
   * Logs all local data items
   */
  protected printAll(): void {
    if (this.sessionStorage.isAvailable()) {
      this.sessionStorage.getAll().forEach((value, key) => log(`[SessionStorage] ${key} = ${value}`));
    }

    if (this.localStorage.isAvailable()) {
      this.localStorage.getAll().forEach((value, key) => log(`[LocalStorage] ${key} = ${value}`));
    }
  }
}
