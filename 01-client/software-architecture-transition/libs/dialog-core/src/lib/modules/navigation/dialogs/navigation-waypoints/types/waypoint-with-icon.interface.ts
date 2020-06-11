import {Waypoint} from '../../../model/waypoint';

export interface WaypointWithIcon {
  waypoint: Waypoint;
  iconUrl: string;
  currentWaypoint: boolean;
}
