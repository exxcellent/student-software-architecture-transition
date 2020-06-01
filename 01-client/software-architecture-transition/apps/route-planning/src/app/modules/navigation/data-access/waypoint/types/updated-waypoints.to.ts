import {WaypointTO} from './waypoint.to';

export interface UpdatedWaypointsTO {
  readonly updatedWaypoint: WaypointTO
  readonly nextWaypoint: WaypointTO
}
