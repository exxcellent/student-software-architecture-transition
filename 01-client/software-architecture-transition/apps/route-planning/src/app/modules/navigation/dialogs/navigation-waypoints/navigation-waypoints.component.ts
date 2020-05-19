import {Component, OnInit} from '@angular/core';
import {NavigationWaypointsDialogCore} from './navigation-waypoints.dialogcore';
import {Waypoint} from '../../model/waypoint';

@Component({
  selector: 'nav-navigation-waypoints',
  templateUrl: './navigation-waypoints.component.html',
  styleUrls: ['./navigation-waypoints.component.css'],
  providers: [NavigationWaypointsDialogCore]
})
export class NavigationWaypointsComponent implements OnInit {

  constructor(private dialogCore: NavigationWaypointsDialogCore) { }

  ngOnInit(): void {

  }

  get waypoints(): Waypoint[] {
    return this.dialogCore.waypoints;
  }
}
