import {Injectable} from '@angular/core';

@Injectable()
export class SessionStorageDataProvider {

  constructor() {

  }

  public isAvailable(): boolean {
    return !!window.sessionStorage;
  }

  public getAll(): Map<string, string> {
    const allItems = new Map();

    for (let i = 0; i < sessionStorage.length; i++) {
      const key = sessionStorage.key(i);
      const value = sessionStorage.getItem(key);
      allItems.set(key, value);
    }

    return allItems;
  }

  public set(key: string, value: string): void {
    sessionStorage.setItem(key, value);
  }

  public get(key: string): string {
    return sessionStorage.getItem(key);
  }

  public remove(key: string): void {
    sessionStorage.removeItem(key);
  }
}
