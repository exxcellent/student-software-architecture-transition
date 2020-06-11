import {createAction, props} from '@ngrx/store';
import {Route} from '../../../../model/route';
import {Waypoint} from '../../../../model/waypoint';

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

const nextDay = createAction(
  '[Route] Switch to next day'
);

const previousDay = createAction(
  '[Route] Switch to previous day'
);

const finishWaypoint = createAction(
  '[Waypoint] Finish',
  props<{ waypointId: number, version: number }>()
);
const cancelWaypoint = createAction(
  '[Waypoint] Cancel',
  props<{ waypointId: number, version: number }>()
);

const updateWaypointSuccess = createAction(
  '[Waypoint] Update success',
  props<{ waypoint: Waypoint, nextWaypoint: Waypoint }>()
);

const updateWaypointFailure = createAction(
  '[Waypoint] Update failed',
  props<{ error: any }>()
);


export const actions = {
  loadMyRouteOfToday,
  loadMyRoute,
  loadMyRouteSuccess,
  loadMyRouteFailure,
  nextDay,
  previousDay,
  finishWaypoint,
  cancelWaypoint,
  updateWaypointSuccess,
  updateWaypointFailure
};
