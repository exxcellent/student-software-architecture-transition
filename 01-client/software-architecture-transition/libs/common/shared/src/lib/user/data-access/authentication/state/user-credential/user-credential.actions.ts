import {createAction, props} from '@ngrx/store';
import {UserCredentials} from '../../../../model/user-credentials';
import {RestResponse} from '../../../../../data-access/types/rest-response.interface';

const loadUserCredentials = createAction(
  '[UserCredential] Load UserCredentials'
);

const loadUserCredentialsSuccess = createAction(
  '[UserCredential] Load UserCredentials Success',
  props<{ data: UserCredentials }>()
);

const loadUserCredentialsFailure = createAction(
  '[UserCredential] Load UserCredentials Failure',
  props<{ error: RestResponse<UserCredentials> }>()
);

export const actions = {
  loadUserCredentials,
  loadUserCredentialsSuccess,
  loadUserCredentialsFailure
};
