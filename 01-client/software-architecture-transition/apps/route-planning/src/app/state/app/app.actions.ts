import {createAction} from '@ngrx/store';

const showPageLoading = createAction(
  '[App] Show Page Loading'
);
const hidePageLoading = createAction(
  '[App] Hide Page Loading'
);
const togglePageLoading = createAction(
  '[App] Toggle Page Loading'
);

export const actions = {
  showPageLoading,
  hidePageLoading,
  togglePageLoading
};
