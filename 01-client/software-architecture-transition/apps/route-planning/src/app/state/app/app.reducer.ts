import {createReducer, on} from '@ngrx/store';
import {actions} from './app.actions';

export const appFeatureKey = 'app';

export interface AppState {
  pageLoading: boolean
}

export const initialState: AppState = {
  pageLoading: false
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
);

const getPageLoading = (state: AppState) => state.pageLoading;

export const getter = {
  getPageLoading
};
