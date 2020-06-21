import {Waypoint} from './waypoint';

export interface Route {
  date: Date;
  totalDurationInSeconds: number;
  timeRemainingInSeconds: number;
  waypoints: Waypoint[];
}
