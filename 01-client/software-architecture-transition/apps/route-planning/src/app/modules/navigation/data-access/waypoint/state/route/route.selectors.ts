import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromRoute from './route.reducer';

const selectRoutesState = createFeatureSelector<fromRoute.RoutesState>(
  fromRoute.routeFeatureKey
);

const selectRoutes = createSelector(selectRoutesState, fromRoute.getter.getRoutes);
const selectRoutesLoading = createSelector(selectRoutesState, fromRoute.getter.getRoutesLoading);
const selectRoutesLoaded = createSelector(selectRoutesState, fromRoute.getter.getRoutesLoaded);
const selectCurrentDay = createSelector(selectRoutesState, fromRoute.getter.getCurrentDay);
const selectCurrentRoute = createSelector(selectRoutesState, fromRoute.getter.getCurrentRoute);
const selectCurrentWaypoints = createSelector(selectRoutesState, fromRoute.getter.getCurrentWaypoints);

export const selectors = {
  selectRoutesState,
  selectRoutes,
  selectRoutesLoading,
  selectRoutesLoaded,
  selectCurrentDay,
  selectCurrentRoute,
  selectCurrentWaypoints,
};
