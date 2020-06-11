import {Waypoint} from '@software-architecture-transition/model/navigation';

export interface WaypointWithIcon {
  waypoint: Waypoint;
  iconUrl: string;
  currentWaypoint: boolean;
}
