import {Component, OnInit} from '@angular/core';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';
import {today} from '../../../shared/functions';

@Component({
  selector: 'nav-navigation-waypoints',
  templateUrl: './navigation-waypoints.component.html',
  styleUrls: ['./navigation-waypoints.component.css']
})
export class NavigationWaypointsComponent implements OnInit {

  routes: any;

  constructor(private waypointRepositoryService: WaypointRepositoryService) { }

  ngOnInit(): void {
    this.waypointRepositoryService.myRouteOfDay$(today()).subscribe(routes => {
      this.routes = routes;
    });
  }
}
