import {WaypointTO} from './waypoint.to';

export interface RouteCTO {
  readonly date: string;
  readonly totalDurationInSeconds: number;
  readonly timeRemainingInSeconds: number;
  readonly waypoints: WaypointTO[];
}
