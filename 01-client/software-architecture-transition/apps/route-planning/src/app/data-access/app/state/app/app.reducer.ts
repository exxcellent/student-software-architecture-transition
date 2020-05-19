import {createReducer, on} from '@ngrx/store';
import {actions} from './app.actions';
import {NotificationMessage} from '../../../../modules/shared/types/notification-message.interface';

export const appFeatureKey = 'app';

export interface AppState {
  pageLoading: boolean,
  notification?: NotificationMessage
}

export const initialState: AppState = {
  pageLoading: false,
};


export const reducer = createReducer(
  initialState,

  on(actions.showPageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: true
    };

    return updatedState;
  }),
  on(actions.hidePageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: false
    };

    return updatedState;
  }),
  on(actions.togglePageLoading, state => {
    const updatedState: AppState = {
      ...state,
      pageLoading: !state.pageLoading
    };

    return updatedState;
  }),
  on(actions.showNotification, (state, action) => {
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

export const getter = {
  getPageLoading,
  getNotification
};
