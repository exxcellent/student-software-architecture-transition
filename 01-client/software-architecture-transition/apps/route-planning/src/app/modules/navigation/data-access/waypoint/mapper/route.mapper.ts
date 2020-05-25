import {RouteCTO} from '../types/route.cto';
import {Route} from '../../../model/route';
import {Waypoint} from '../../../model/waypoint';
import {WaypointTO} from '../types/waypoint.to';
import {Status} from '../types/waypoint-status.enum';
import {WaypointStatus} from '../../../model/waypoint-status.enum';
import {Category} from '../types/waypoint-category.enum';
import {WaypointCategory} from '../../../model/waypoint-category.enum';

export function fromResponse(response: RouteCTO): Route {
  return {
    date: new Date(Date.parse(response.date)),
    timeRemainingInSeconds: response.timeRemainingInSeconds,
    totalDurationInSeconds: response.totalDurationInSeconds,
    waypoints: response.waypoints.map<Waypoint>((waypointTO: WaypointTO) => {
      const waypoint: Waypoint = {
        waypointId: waypointTO.waypointId,
        inspectorId: waypointTO.inspectorId,
        appointmentId: waypointTO.appointmentId,
        date: new Date(Date.parse(waypointTO.date)),
        orderIndex: waypointTO.orderIndex,
        category: fromResponseCategory(waypointTO.category),
        status: fromResponseStatus(waypointTO.status),
        address: waypointTO.location.address,
        location: { lat: waypointTO.location.latitude, lng: waypointTO.location.longitude },
        contact: {
          name: waypointTO.contact.name,
          phoneNumber: waypointTO.contact.phoneNumber,
          email: waypointTO.contact.email
        }
      };

      return waypoint;
    })
  }
}

function fromResponseStatus(responseStatus): WaypointStatus {
  switch (responseStatus) {
    case Status[Status.ACTIVE]: return WaypointStatus.ACTIVE;
    case Status[Status.FINISHED]: return WaypointStatus.FINISHED;
    case Status[Status.CANCELED]: return WaypointStatus.CANCELED;
    case Status[Status.PENDING]:
    default:
      return WaypointStatus.PENDING;
  }
}

function fromResponseCategory(responseCategory: Category): WaypointCategory {
  switch (responseCategory) {
    case Category.ALL: return WaypointCategory.ALL;
    case Category.PRIVATE: return WaypointCategory.PRIVATE;
    case Category.GAS_STATION: return WaypointCategory.GAS_STATION;
    case Category.APPOINTMENT:
    default:
      return WaypointCategory.APPOINTMENT;
  }
}
