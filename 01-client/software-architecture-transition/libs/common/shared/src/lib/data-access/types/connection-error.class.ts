import {ErrorCategory} from './error-category.enum';
import {NotificationLevel, NotificationMessage} from '../../types';

export class ConnectionError {

  constructor(public status: number, public message?: string, public url?: string) {}

  get errorCategory(): ErrorCategory {
    if (this.status === 0) {
      return ErrorCategory.INTERNAL;
    } else if (this.status >= 400 && this.status < 500) {
      return ErrorCategory.BUSINESS
    } else {
      return ErrorCategory.TECHNICAL
    }
  }

  get asNotification(): NotificationMessage {
    return {
      level: this.notificationLevel,
      message: this.message
    };
  }

  get notificationLevel(): NotificationLevel {
    if (this.status >= 400 && this.status < 500) {
      return NotificationLevel.WARN
    } else {
      return NotificationLevel.ERROR
    }
  }
}
