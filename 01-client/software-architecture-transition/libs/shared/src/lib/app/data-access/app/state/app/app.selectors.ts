import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromApp from './app.reducer';
import {appStateGetter} from './app.reducer';

const selectAppState = createFeatureSelector<fromApp.AppState>(
  fromApp.appFeatureKey
);

const selectPageLoading = createSelector(selectAppState, appStateGetter.getPageLoading);
const selectNotification = createSelector(selectAppState, appStateGetter.getNotification);

export const appStateSelectors = {
  selectAppState,
  selectPageLoading,
  selectNotification
};
