import {WaypointCategory} from './waypoint-category.enum';
import {WaypointStatus} from './waypoint-status.enum';

export interface Waypoint {

  waypointId: number;
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
}
