import {environment} from '@software-architecture-transition/environments';

export function log(message: string): void {
  if (!environment.production) {
    console.log(message);
  }
}

export function logWarn(message: string): void {
  if (!environment.production) {
    console.warn(message);
  }
}

export function logError(message: string, exception?: any): void {
  if (!environment.production) {
    console.error(message, exception);
  }
}
