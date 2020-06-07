import {WaypointCategory} from './waypoint-category.enum';
import {WaypointStatus} from './waypoint-status.enum';
import {Notification} from './notification';

export interface Waypoint {

  waypointId: number;
  version: number;
  inspectorId: number;
  appointmentId: number;

  date: Date;
  orderIndex: number;
  category: WaypointCategory;
  status: WaypointStatus;

  address: string;
  location: { lat: number, lng: number }

  contact: {
     name: string;
     phoneNumber: string;
     email: string;
  }

  notifications?: Notification[];
}
