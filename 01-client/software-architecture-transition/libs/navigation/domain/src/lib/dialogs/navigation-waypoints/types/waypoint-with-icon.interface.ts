import {Waypoint} from '@software-architecture-transition/dialog-core/navigation';

export interface WaypointWithIcon {
  waypoint: Waypoint;
  iconUrl: string;
  currentWaypoint: boolean;
}
