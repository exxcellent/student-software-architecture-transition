import {Component, OnInit} from '@angular/core';
import {NavigationWaypointsDialogCore} from './navigation-waypoints.dialogcore';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {TruncatePipe} from '../../../shared/pipes';
import {WaypointCategory} from '../../model/waypoint-category.enum';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';

@Component({
  selector: 'nav-navigation-waypoints',
  templateUrl: './navigation-waypoints.component.html',
  styleUrls: ['./navigation-waypoints.component.css'],
  providers: [NavigationWaypointsDialogCore, TruncatePipe]
})
export class NavigationWaypointsComponent implements OnInit {

  WaypointCategory = WaypointCategory;

  dragableWaypoints: WaypointWithIcon[];

  constructor(private dialogCore: NavigationWaypointsDialogCore) { }

  ngOnInit(): void {
    // initial waypoint list
    this.dragableWaypoints = this.dialogCore.waypoints;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.dragableWaypoints, event.previousIndex, event.currentIndex);

  }
}
