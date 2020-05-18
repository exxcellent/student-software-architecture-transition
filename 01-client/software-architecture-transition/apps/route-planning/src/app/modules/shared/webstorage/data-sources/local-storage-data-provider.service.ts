import {Injectable} from '@angular/core';

@Injectable()
export class LocalStorageDataProvider {

  constructor() {
  }

  public isAvailable(): boolean {
    return !!window.localStorage;
  }

  public getAll(): Map<string, string> {
    const allItems = new Map();

    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      const value = localStorage.getItem(key);
      allItems.set(key, value);
    }

    return allItems;
  }

  public set(key: string, value: string): void {
    localStorage.setItem(key, value);
  }

  public get(key: string): string {
    return localStorage.getItem(key);
  }

  public remove(key: string): void {
    localStorage.removeItem(key);
  }
}
