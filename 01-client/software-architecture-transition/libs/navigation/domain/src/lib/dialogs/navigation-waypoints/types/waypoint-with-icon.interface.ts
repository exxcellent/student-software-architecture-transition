import {WaypointDTO} from '../../../dataaccess';

export interface WaypointWithIcon {
  waypoint: WaypointDTO;
  iconUrl: string;
  currentWaypoint: boolean;
}
