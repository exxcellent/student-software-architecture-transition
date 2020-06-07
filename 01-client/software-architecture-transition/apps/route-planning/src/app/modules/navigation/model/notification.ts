import {NotificationChannel} from './notification-channel.enum';

export interface Notification {
  waypointId?: number;
  notificationId?: number;
  version?: number;
  channel: NotificationChannel;
  notifiedAt: Date;
  arrivalTimeInSeconds?: number
}
