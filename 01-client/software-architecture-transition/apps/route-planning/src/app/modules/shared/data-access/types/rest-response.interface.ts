import {RequestResult} from './request-result.enum';

export interface RestResponse<T> {
  payload?: T;
  result: RequestResult;
}
