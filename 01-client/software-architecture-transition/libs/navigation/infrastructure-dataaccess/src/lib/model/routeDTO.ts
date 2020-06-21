import {WaypointDTO} from './waypointDTO';

export interface RouteDTO {
  date: Date;
  totalDurationInSeconds: number;
  timeRemainingInSeconds: number;
  waypoints: WaypointDTO[];
}
