import {NotificationLevel} from './notification-level.enum';

export interface NotificationMessage {
  level: NotificationLevel
  message: string
}
