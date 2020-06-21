import {createAction, props} from '@ngrx/store';
import {NotificationMessage} from '../../../../../types';

const showPageLoading = createAction(
  '[App] Show Page Loading'
);
const hidePageLoading = createAction(
  '[App] Hide Page Loading'
);
const togglePageLoading = createAction(
  '[App] Toggle Page Loading'
);
const showNotification = createAction(
  '[App] Show Notification',
  props<{ data: NotificationMessage }>()
);

export const appStateActions = {
  showPageLoading,
  hidePageLoading,
  togglePageLoading,
  showNotification
};
