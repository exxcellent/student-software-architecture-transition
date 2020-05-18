import {WaypointContactTO} from './waypoint-contact.to';
import {WaypointLocationTO} from './waypoint-location.to';
import {Status} from './waypoint-status.enum';
import {Category} from './waypoint-category.enum';

export interface WaypointTO {
  readonly waypointId: number;
  readonly date: string;
  readonly orderIndex: number;
  readonly category: Category;
  readonly status: Status;

  readonly inspectorId: number;
  readonly appointmentId: number;

  readonly contact: WaypointContactTO;
  readonly location: WaypointLocationTO;
}
