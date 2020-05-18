import {createAction, props} from '@ngrx/store';
import {Route} from '../../../../model/route';

const loadMyRouteOfToday = createAction(
  '[Route] Load My Route of Today'
);

const loadMyRoute = createAction(
  '[Route] Load My Route'
);

const loadMyRouteSuccess = createAction(
  '[Route] Load My Route Success',
  props<{ data: Route }>()
);

const loadMyRouteFailure = createAction(
  '[Route] Load My Route Failure',
  props<{ error: any }>()
);

export const actions = {
  loadMyRouteOfToday,
  loadMyRoute,
  loadMyRouteSuccess,
  loadMyRouteFailure
};
