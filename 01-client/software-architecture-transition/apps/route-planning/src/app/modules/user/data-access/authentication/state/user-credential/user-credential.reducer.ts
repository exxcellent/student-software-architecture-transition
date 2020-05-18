import {createReducer, on} from '@ngrx/store';
import {actions} from './user-credential.actions';
import {UserCredentials} from '../../../../model/user-credentials';

export const userCredentialFeatureKey = 'userCredential';

export interface UserCredentialState {
  userCredentials: UserCredentials;
  loading: boolean;
  loaded: boolean;
  authenticated: boolean
}

export const initialState: UserCredentialState = {
  userCredentials: {
    user: null,
    password: null,
    jwtToken: null
  },
  loading: false,
  loaded: true,
  authenticated: false
};


export const reducer = createReducer(
  initialState,

  on(actions.loadUserCredentials, state => {
    const updatedState: UserCredentialState = {
      ...state,
      loading: true,
      loaded: false
    };

    return updatedState;
  }),
  on(actions.loadUserCredentialsSuccess, (state, action) => {
    const updatedState: UserCredentialState = {
      ...state,
      userCredentials: action.data,
      loading: false,
      loaded: true,
      authenticated: true
    };

    return updatedState;
  }),
  on(actions.loadUserCredentialsFailure, (state, action) => {
    const updatedState: UserCredentialState = {
      ...state,
      loading: false,
      loaded: true,
      authenticated: false
    };

    return updatedState;
  }),

);

const getUserCredentialsLoading = (state: UserCredentialState) => state.loading;
const getUserCredentialsLoaded = (state: UserCredentialState) => state.loaded;
const getUserCredentialsAuthenticated = (state: UserCredentialState) => state.authenticated;
const getUserCredentials = (state: UserCredentialState) => state.userCredentials;
const getUserCredentialsJwtToken = (state: UserCredentialState) => state.userCredentials.jwtToken;
const getUserCredentialsValidUntil = (state: UserCredentialState) => state.userCredentials.validUntil;

export const getter = {
  getUserCredentialsLoading,
  getUserCredentialsLoaded,
  getUserCredentialsAuthenticated,
  getUserCredentials,
  getUserCredentialsJwtToken,
  getUserCredentialsValidUntil
};

