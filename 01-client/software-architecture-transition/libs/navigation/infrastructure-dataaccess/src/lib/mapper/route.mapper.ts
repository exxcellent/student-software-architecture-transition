import {RouteCTO} from '../types/route.cto';
import {WaypointTO} from '../types/waypoint.to';
import {Status} from '../types/waypoint-status.enum';
import {Category} from '../types/waypoint-category.enum';
import {toISODateString} from '@software-architecture-transition/shared';
import {UpdatedWaypointsTO} from '../types/updated-waypoints.to';
import {Route, Waypoint, WaypointCategory, WaypointStatus} from '../model';


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
