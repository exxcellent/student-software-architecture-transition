import {Component, OnInit} from '@angular/core';
import {NavigationWaypointsDialogCore} from './navigation-waypoints.dialogcore';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {TruncatePipe} from '../../../shared/pipes/truncate.pipe';
import {WaypointCategory} from '../../model/waypoint-category.enum';

@Component({
  selector: 'nav-navigation-waypoints',
  templateUrl: './navigation-waypoints.component.html',
  styleUrls: ['./navigation-waypoints.component.css'],
  providers: [NavigationWaypointsDialogCore, TruncatePipe]
})
export class NavigationWaypointsComponent implements OnInit {

  WaypointCategory = WaypointCategory;
  constructor(private dialogCore: NavigationWaypointsDialogCore) { }

  ngOnInit(): void {

  }

  get waypoints(): WaypointWithIcon[] {
    return this.dialogCore.waypoints;
  }
}
