import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromApp from './app.reducer';
import {getter} from './app.reducer';

const selectAppState = createFeatureSelector<fromApp.AppState>(
  fromApp.appFeatureKey
);

const selectPageLoading = createSelector(selectAppState, getter.getPageLoading);

export const selectors = {
  selectAppState,
  selectPageLoading
};
