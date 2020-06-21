import {RouteCTO} from '../types/route.cto';
import {WaypointTO} from '../types/waypoint.to';
import {Status} from '../types/waypoint-status.enum';
import {Category} from '../types/waypoint-category.enum';
import {toISODateString} from '@software-architecture-transition/shared';
import {UpdatedWaypointsTO} from '../types/updated-waypoints.to';
import {RouteDTO, WaypointCategoryDTO, WaypointDTO, WaypointStatusDTO} from '../model';


export function fromResponse(response: RouteCTO): RouteDTO {
  return {
    date: new Date(Date.parse(response.date)),
    timeRemainingInSeconds: response.timeRemainingInSeconds,
    totalDurationInSeconds: response.totalDurationInSeconds,
    waypoints: response.waypoints.map<WaypointDTO>(fromWaypointResponse)
  }
}
export function fromUpdatedWaypointResponse(updatedWaypointsTO: UpdatedWaypointsTO): WaypointDTO[] {
  return [
    fromWaypointResponse(updatedWaypointsTO.updatedWaypoint),
    fromWaypointResponse(updatedWaypointsTO.nextWaypoint)
  ]
}

export function fromWaypointResponse(waypointTO: WaypointTO): WaypointDTO {
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


export function toWaypointRequest(waypoint: WaypointDTO): WaypointTO {
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

function fromResponseStatus(responseStatus: string): WaypointStatusDTO {
  switch (responseStatus) {
    case Status[Status.ACTIVE]: return WaypointStatusDTO.ACTIVE;
    case Status[Status.FINISHED]: return WaypointStatusDTO.FINISHED;
    case Status[Status.CANCELED]: return WaypointStatusDTO.CANCELED;
    case Status[Status.PENDING]:
    default:
      return WaypointStatusDTO.PENDING;
  }
}

function fromResponseCategory(responseCategory: string): WaypointCategoryDTO {
  switch (responseCategory) {
    case Category[Category.ALL]: return WaypointCategoryDTO.ALL;
    case Category[Category.PRIVATE]: return WaypointCategoryDTO.PRIVATE;
    case Category[Category.GAS_STATION]: return WaypointCategoryDTO.GAS_STATION;
    case Category[Category.APPOINTMENT]:
    default:
      return WaypointCategoryDTO.APPOINTMENT;
  }
}


function toRequestStatus(waypointStatus: WaypointStatusDTO): string {
  switch (waypointStatus) {
    case WaypointStatusDTO.ACTIVE: return Status[Status.ACTIVE];
    case WaypointStatusDTO.FINISHED: return Status[Status.FINISHED];
    case WaypointStatusDTO.CANCELED: return Status[Status.CANCELED];
    case WaypointStatusDTO.PENDING:
    default:
      return Status[Status.PENDING];
  }
}

function toRequestCategory(waypointCategory: WaypointCategoryDTO): string {
  switch (waypointCategory) {
    case WaypointCategoryDTO.ALL: return Category[Category.ALL];
    case WaypointCategoryDTO.PRIVATE: return Category[Category.PRIVATE];
    case WaypointCategoryDTO.GAS_STATION: return Category[Category.GAS_STATION];
    case WaypointCategoryDTO.APPOINTMENT:
    default:
      return Category[Category.APPOINTMENT];
  }
}
