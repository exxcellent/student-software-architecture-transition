import {createReducer, on} from '@ngrx/store';
import {actions} from './route.actions';
import {Route} from '../../../../model/route';
import {toISODateString} from '../../../../../shared/functions';

export const routeFeatureKey = 'route';

export interface RoutesState {
  routes: {
    [key: string]: {  // routes: { '2020-05-09': { myRoute: { ... Route ...}, otherRoutes: { 2: { ... Route ... } } } }
      myRoute: Route
    }
  },
  loading: boolean,
  loaded: boolean
}

export const initialState: RoutesState = {
  routes: {

  },
  loading: false,
  loaded: false
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
    const updatedState: RoutesState = {
      ...state,
      loading: false,
      loaded: false
    };

    return updatedState;
  }),
);

const getRoutes = (state: RoutesState) => state.routes;
const getRoutesLoading = (state: RoutesState) => state.loading;
const getRoutesLoaded = (state: RoutesState) => state.loaded;

export const getter = {
  getRoutes,
  getRoutesLoading,
  getRoutesLoaded
};

