import {createAction, props} from '@ngrx/store';
import {Route} from '../../../../model/route';
import {Waypoint} from '../../../../model/waypoint';
import {Notification} from '../../../../model/notification';

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

const notifyContact = createAction(
  '[Notification] Send notification',
  props<{ waypointId: number, version: number }>()
);

const notifyContactSuccess = createAction(
  '[Notification] Send notification success',
  props<{ waypoint: Waypoint, notification: Notification }>()
);

const notifyContactFailure = createAction(
  '[Notification] Send notification failed',
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
  updateWaypointFailure,
  notifyContact,
  notifyContactSuccess,
  notifyContactFailure
};
