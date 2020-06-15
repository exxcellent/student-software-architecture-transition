import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromUserCredential from './user-credential.reducer';


const selectUserCredentialState = createFeatureSelector<fromUserCredential.UserCredentialState>(
  fromUserCredential.userCredentialFeatureKey
);

const selectUserCredentials = createSelector(selectUserCredentialState, fromUserCredential.getter.getUserCredentials);
const selectUserCredentialsJwtToken = createSelector(selectUserCredentialState, fromUserCredential.getter.getUserCredentialsJwtToken);
const selectUserCredentialsLoading = createSelector(selectUserCredentialState, fromUserCredential.getter.getUserCredentialsLoading);
const selectUserCredentialsLoaded = createSelector(selectUserCredentialState, fromUserCredential.getter.getUserCredentialsLoaded);
const selectUserAuthenticated = createSelector(selectUserCredentialState, fromUserCredential.getter.getUserCredentialsAuthenticated);

export const selectors = {
  selectUserCredentialState,
  selectUserCredentials,
  selectUserCredentialsJwtToken,
  selectUserCredentialsLoading,
  selectUserCredentialsLoaded,
  selectUserAuthenticated
};

