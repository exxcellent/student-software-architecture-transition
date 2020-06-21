import {WaypointDTO} from './waypoint';

export interface RouteDTO {
  date: Date;
  totalDurationInSeconds: number;
  timeRemainingInSeconds: number;
  waypoints: WaypointDTO[];
}
