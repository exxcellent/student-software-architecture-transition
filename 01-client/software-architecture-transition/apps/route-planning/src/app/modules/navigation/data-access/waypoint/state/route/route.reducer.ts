import {createReducer, on} from '@ngrx/store';
import {actions} from './route.actions';
import {Route} from '../../../../model/route';
import {modifyDay, today, toISODateString} from '../../../../../shared/functions';
import {ConnectionErrorState, ErrorCategory} from '../../../../../shared/data-access';

export const routeFeatureKey = 'route';

export interface RoutesState {
  routes: {
    [key: string]: {  // routes: { '2020-05-09': { myRoute: { ... Route ...}, otherRoutes: { 2: { ... Route ... } } } }
      myRoute: Route
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
);

const getRoutes = (state: RoutesState) => state.routes;
const getRoutesLoading = (state: RoutesState) => state.loading;
const getRoutesLoaded = (state: RoutesState) => state.loaded;
const getCurrentDay = (state: RoutesState) => state.currentDay;

export const getter = {
  getRoutes,
  getRoutesLoading,
  getRoutesLoaded,
  getCurrentDay
};

