import {WaypointCategoryDTO} from './waypoint-category.enum';
import {WaypointStatusDTO} from './waypoint-status.enum';

export interface WaypointDTO {

  waypointId: number;
  version: number;
  inspectorId: number;
  appointmentId: number;

  date: Date;
  orderIndex: number;
  category: WaypointCategoryDTO;
  status: WaypointStatusDTO;

  address: string;
  location: { lat: number, lng: number }

  contact: {
     name: string;
     phoneNumber: string;
     email: string;
  }
}
