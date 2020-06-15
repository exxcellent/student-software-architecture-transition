import {WaypointContactTO} from './waypoint-contact.to';
import {WaypointLocationTO} from './waypoint-location.to';

export interface WaypointTO {
  readonly waypointId: number;
  readonly version: number;
  readonly date: string;
  readonly orderIndex: number;
  readonly category: string;
  readonly status: string;

  readonly inspectorId: number;
  readonly appointmentId: number;

  readonly contact: WaypointContactTO;
  readonly location: WaypointLocationTO;
}
