import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromRoute from './route.reducer';

const selectRoutesState = createFeatureSelector<fromRoute.RoutesState>(
  fromRoute.routeFeatureKey
);

const selectRoutes = createSelector(selectRoutesState, fromRoute.getter.getRoutes);
const selectRoutesLoading = createSelector(selectRoutesState, fromRoute.getter.getRoutesLoading);
const selectRoutesLoaded = createSelector(selectRoutesState, fromRoute.getter.getRoutesLoaded);

export const selectors = {
  selectRoutesState,
  selectRoutes,
  selectRoutesLoading,
  selectRoutesLoaded
};
