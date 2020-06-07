import {RouteCTO} from '../types/route.cto';
import {Route} from '../../../model/route';
import {Waypoint} from '../../../model/waypoint';
import {WaypointTO} from '../types/waypoint.to';
import {Status} from '../types/waypoint-status.enum';
import {WaypointStatus} from '../../../model/waypoint-status.enum';
import {Category} from '../types/waypoint-category.enum';
import {WaypointCategory} from '../../../model/waypoint-category.enum';
import {toISODateString} from '../../../../shared/functions';
import {UpdatedWaypointsTO} from '../types/updated-waypoints.to';
import {Notification} from '../../../model/notification';
import {NotificationTO} from '../types/notification.to';
import {NotificationChannel} from '../../../model/notification-channel.enum';

export function fromResponse(response: RouteCTO): Route {
  return {
    date: new Date(Date.parse(response.date)),
    timeRemainingInSeconds: response.timeRemainingInSeconds,
    totalDurationInSeconds: response.totalDurationInSeconds,
    waypoints: response.waypoints.map<Waypoint>(fromWaypointResponse)
  }
}
export function fromUpdatedWaypointResponse(updatedWaypointsTO: UpdatedWaypointsTO): Waypoint[] {
  return [
    fromWaypointResponse(updatedWaypointsTO.updatedWaypoint),
    fromWaypointResponse(updatedWaypointsTO.nextWaypoint)
  ]
}

export function fromWaypointResponse(waypointTO: WaypointTO): Waypoint {
  return {
        waypointId: waypointTO.waypointId,
        version: waypointTO.version,
        inspectorId: waypointTO.inspectorId,
        appointmentId: waypointTO.appointmentId,
        date: new Date(Date.parse(waypointTO.date)),
        orderIndex: waypointTO.orderIndex,
        category: fromResponseCategory(waypointTO.category),
        status: fromResponseStatus(waypointTO.status),
        address: waypointTO.location.address,
        location: {lat: waypointTO.location.latitude, lng: waypointTO.location.longitude},
        contact: {
          name: waypointTO.contact.name,
          phoneNumber: waypointTO.contact.phoneNumber,
          email: waypointTO.contact.email
        }
      };
}

export function toWaypointRequest(waypoint: Waypoint): WaypointTO {
  return {
    waypointId: waypoint.waypointId,
    version: waypoint.version,
    inspectorId: waypoint.inspectorId,
    appointmentId: waypoint.appointmentId,
    date: toISODateString(waypoint.date),
    orderIndex: waypoint.orderIndex,
    category: toRequestCategory(waypoint.category),
    status: toRequestStatus(waypoint.status),
    location: {
      address: waypoint.address,
      latitude: waypoint.location.lat,
      longitude: waypoint.location.lng
    },
    contact: {
      name: waypoint.contact.name,
      phoneNumber: waypoint.contact.phoneNumber,
      email: waypoint.contact.email
    }
  };
}

export function toNotificationRequest(waypoint: Waypoint, notification: Notification): NotificationTO {
  return {
    waypointId: waypoint.waypointId,
    channel: toRequestChannel(notification.channel),

    notificationId: notification.notificationId ?? undefined,
    version: notification.version ?? undefined,
    arrivalTimeInSeconds: notification.arrivalTimeInSeconds ?? undefined,
    notifiedAt: notification.notifiedAt.toISOString()
  };
}

export function fromNotificationResponse(response: NotificationTO): Notification {
  return {
    waypointId: response.waypointId,
    channel: fromResponseChannel(response.channel),

    notificationId: response.notificationId ?? undefined,
    version: response.version ?? undefined,
    arrivalTimeInSeconds: response.arrivalTimeInSeconds ?? undefined,
    notifiedAt: new Date(Date.parse(response.notifiedAt))
  };
}


function fromResponseStatus(responseStatus: string): WaypointStatus {
  switch (responseStatus) {
    case Status[Status.ACTIVE]: return WaypointStatus.ACTIVE;
    case Status[Status.FINISHED]: return WaypointStatus.FINISHED;
    case Status[Status.CANCELED]: return WaypointStatus.CANCELED;
    case Status[Status.PENDING]:
    default:
      return WaypointStatus.PENDING;
  }
}

function fromResponseCategory(responseCategory: string): WaypointCategory {
  switch (responseCategory) {
    case Category[Category.ALL]: return WaypointCategory.ALL;
    case Category[Category.PRIVATE]: return WaypointCategory.PRIVATE;
    case Category[Category.GAS_STATION]: return WaypointCategory.GAS_STATION;
    case Category[Category.APPOINTMENT]:
    default:
      return WaypointCategory.APPOINTMENT;
  }
}


function toRequestStatus(waypointStatus: WaypointStatus): string {
  switch (waypointStatus) {
    case WaypointStatus.ACTIVE: return Status[Status.ACTIVE];
    case WaypointStatus.FINISHED: return Status[Status.FINISHED];
    case WaypointStatus.CANCELED: return Status[Status.CANCELED];
    case WaypointStatus.PENDING:
    default:
      return Status[Status.PENDING];
  }
}

function toRequestCategory(waypointCategory: WaypointCategory): string {
  switch (waypointCategory) {
    case WaypointCategory.ALL: return Category[Category.ALL];
    case WaypointCategory.PRIVATE: return Category[Category.PRIVATE];
    case WaypointCategory.GAS_STATION: return Category[Category.GAS_STATION];
    case WaypointCategory.APPOINTMENT:
    default:
      return Category[Category.APPOINTMENT];
  }
}

function toRequestChannel(notifcationChannel: NotificationChannel): string {
  switch (notifcationChannel) {
    case NotificationChannel.SMS: return NotificationChannel[NotificationChannel.SMS];
    case NotificationChannel.PHONE: return NotificationChannel[NotificationChannel.PHONE];
    case NotificationChannel.EMAIL: return NotificationChannel[NotificationChannel.EMAIL];
    case NotificationChannel.AUTOMATIC:
    default:
      return NotificationChannel[NotificationChannel.AUTOMATIC];
  }
}

function fromResponseChannel(notifcationChannel: string): NotificationChannel {
  switch (notifcationChannel) {
    case NotificationChannel[NotificationChannel.SMS]: return NotificationChannel.SMS;
    case NotificationChannel[NotificationChannel.PHONE]: return NotificationChannel.PHONE;
    case NotificationChannel[NotificationChannel.EMAIL]: return NotificationChannel.EMAIL;
    case NotificationChannel[NotificationChannel.AUTOMATIC]:
    default:
      return NotificationChannel.AUTOMATIC;
  }
}
