import {createReducer, on} from '@ngrx/store';
import {actions} from './route.actions';
import {
  ConnectionErrorState,
  ErrorCategory,
  modifyDay,
  today,
  toISODateString
} from '@software-architecture-transition/shared';
import {RouteDTO, WaypointDTO} from '../../model';

export const routeFeatureKey = 'route';

export interface RoutesState {
  routes: {
    [key: string]: {  // routes: { '2020-05-09': { myRoute: { ... Route ...}, otherRoutes: { 2: { ... Route ... } } } }
      myRoute: RouteDTO
    }
  },
  currentDay: Date,
  loading: boolean,
  loaded: boolean,
  error: ConnectionErrorState
}

export const initialState: RoutesState = {
  routes: {

  },
  currentDay: today(),
  loading: false,
  loaded: false,
  error: null
};


export const reducer = createReducer(
  initialState,

  on(actions.loadMyRoute, state => {
    const updatedState: RoutesState = {
      ...state,
      loading: true,
      loaded: false
    };

    return updatedState;
  }),
  on(actions.loadMyRouteSuccess, (state, action) => {
    const myRoute = action.data;

    const updatedState: RoutesState = {
      ...state,
      routes: {
        ...state.routes,
        [toISODateString(myRoute.date)]: {
          myRoute: myRoute,
        }
      },
      loading: false,
      loaded: true
    };

    return updatedState;
  }),
  on(actions.loadMyRouteFailure, (state, action) => {
    console.log('Load Routes Failure: ' + JSON.stringify(action.error));

    const updatedState: RoutesState = {
      ...state,
      loading: false,
      loaded: false,
      error: {
        category: ErrorCategory.BUSINESS
      }
    };

    return updatedState;
  }),

  on(actions.nextDay, state => {
    const updatedState: RoutesState = {
      ...state,
      currentDay: modifyDay(state.currentDay, 1)
    };

    return updatedState;
  }),
  on(actions.previousDay, state => {
    const updatedState: RoutesState = {
      ...state,
      currentDay: modifyDay(state.currentDay, -1)
    };

    return updatedState;
  }),

  on(actions.updateWaypointSuccess, (state, action) => {
    const updatedWaypoint: WaypointDTO = action.waypoint;
    const nextWaypoint: WaypointDTO = action.nextWaypoint;

    const myRoute = state.routes[toISODateString(updatedWaypoint.date)].myRoute;
    const oldWaypoints = myRoute.waypoints;
    const updatedWaypoints: WaypointDTO[] = [];

    oldWaypoints.forEach((waypoint: WaypointDTO) => {
      if (waypoint.waypointId === updatedWaypoint.waypointId) {
        updatedWaypoints.push(updatedWaypoint);
      } else if (waypoint.waypointId === nextWaypoint.waypointId) {
        updatedWaypoints.push(nextWaypoint);
      } else {
        updatedWaypoints.push(waypoint);
      }
    });

    const updatedState: RoutesState = {
      ...state,
      routes: {
        ...state.routes,
        [toISODateString(updatedWaypoint.date)]: {
          myRoute: {
            ...myRoute,
            waypoints: updatedWaypoints
          }
        }
      },
      loading: false,
      loaded: true,
    };

    return updatedState;
  }),

  on(actions.finishWaypoint, (state, action) => {
    const waypointId: number = action.waypointId;
    console.log('[STORE] finishWaypoint set QUEUED = ', waypointId);

    const updatedState: RoutesState = {
      ...state,
      loaded: false,
      loading: true
    };

    return updatedState;
  }),
);

const getRoutes = (state: RoutesState) => state.routes;
const getRoutesLoading = (state: RoutesState) => state.loading;
const getRoutesLoaded = (state: RoutesState) => state.loaded;
const getCurrentDay = (state: RoutesState) => state.currentDay;
const getCurrentRoute = (state: RoutesState) => state.routes[toISODateString(state.currentDay)].myRoute;
const getCurrentWaypoints = (state: RoutesState) => {
  console.log('[STORE] getCurrentWaypoints', state.routes[toISODateString(state.currentDay)].myRoute.waypoints);

  return state.routes[toISODateString(state.currentDay)].myRoute.waypoints;
};


export const getter = {
  getRoutes,
  getRoutesLoading,
  getRoutesLoaded,
  getCurrentDay,
  getCurrentRoute,
  getCurrentWaypoints
};

