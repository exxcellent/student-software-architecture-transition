import {createReducer, on} from '@ngrx/store';
import {appStateActions} from './app.actions';
import {NotificationMessage} from '@software-architecture-transition/shared';

export const appFeatureKey = 'app';

export interface AppState {
  pageLoading: boolean,
  notification?: NotificationMessage
}

export const initialAppStateState: AppState = {
  pageLoading: false,
};


export const appStateReducer = createReducer(
  initialAppStateState,

  on(appStateActions.showPageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: true
    };

    return updatedState;
  }),
  on(appStateActions.hidePageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: false
    };

    return updatedState;
  }),
  on(appStateActions.togglePageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: !state.pageLoading
    };

    return updatedState;
  }),
  on(appStateActions.showNotification, (state, action) => {
    const updatedState: AppState = {
      ...state,
      pageLoading: false,
      notification: action.data
    };

    return updatedState;
  }),
);

const getPageLoading = (state: AppState) => state.pageLoading;
const getNotification = (state: AppState) => state.notification;

export const appStateGetter = {
  getPageLoading,
  getNotification
};
