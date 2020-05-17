import {COMMON_ENVIRONMENT} from './common-environment';
import {Environment} from './environment.interface';

export const environment: Environment = {
  ...COMMON_ENVIRONMENT,

  production: true,
  // @ts-ignore
  googleMapsApiKey: process.env.GOOGLE_MAPS_API_KEY
};
