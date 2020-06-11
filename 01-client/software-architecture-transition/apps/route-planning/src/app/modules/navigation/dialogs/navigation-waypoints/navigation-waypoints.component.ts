import {Component, OnInit} from '@angular/core';
import {NavigationWaypointsDialogCore} from './navigation-waypoints.dialogcore';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {TruncatePipe} from '@software-architecture-transition/shared';
import {WaypointCategory} from '../../model/waypoint-category.enum';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {WaypointStatus} from '../../model/waypoint-status.enum';

@Component({
  selector: 'nav-navigation-waypoints',
  templateUrl: './navigation-waypoints.component.html',
  styleUrls: ['./navigation-waypoints.component.css'],
  providers: [NavigationWaypointsDialogCore, TruncatePipe]
})
export class NavigationWaypointsComponent implements OnInit {

  WaypointCategory = WaypointCategory;
  WaypointStatus = WaypointStatus;

  dragableWaypoints: WaypointWithIcon[];

  constructor(private dialogCore: NavigationWaypointsDialogCore) { }

  ngOnInit(): void {
    // initial waypoint list
    // this.dragableWaypoints = this.dialogCore.waypoints;
    this.dialogCore.currentWaypointsWithIconsSubject$.subscribe((waypoints: WaypointWithIcon[]) => {
      this.dragableWaypoints = waypoints;
    })

  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.dragableWaypoints, event.previousIndex, event.currentIndex);

  }

  isCurrentWaypoint(waypoint: WaypointWithIcon): boolean {
    return this.dialogCore.isNextWaypoint(waypoint.waypoint);
  }

  currentWaypointClass(waypoint: WaypointWithIcon): string {
    return this.isCurrentWaypoint(waypoint) ? 'current-waypoint' : '';
  }

  openDetails(waypoint: WaypointWithIcon): void {
    this.dialogCore.openDetails(waypoint.waypoint);
  }
}
