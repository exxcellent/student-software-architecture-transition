import {Component, OnInit} from '@angular/core';
import {NavigationWaypointDetailsDialogCore} from './navigation-waypoint-details.dialogcore';
import {Waypoint} from '../../model/waypoint';
import {WaypointCategory} from '../../model/waypoint-category.enum';
import {WaypointStatus} from '../../model/waypoint-status.enum';
import {SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'nav-navigation-waypoint-details',
  templateUrl: './navigation-waypoint-details.component.html',
  styleUrls: ['./navigation-waypoint-details.component.css'],
  providers: [NavigationWaypointDetailsDialogCore]
})
export class NavigationWaypointDetailsComponent implements OnInit {

  WaypointCategory = WaypointCategory;
  WaypointStatus = WaypointStatus;

  constructor(private dialogCore: NavigationWaypointDetailsDialogCore) { }

  ngOnInit(): void {

  }

  get isCurrentWaypoint(): boolean {
    return this.dialogCore.isCurrentWaypoint;
  }

  get isPendingWaypoint(): boolean {
    return this.dialogCore.isPendingWaypoint;
  }

  get waypoint(): Waypoint {
    return this.dialogCore.waypoint;
  }

  get callLink(): string {
    return this.dialogCore.callContactLink;
  }

  get mailLink(): string {
    return this.dialogCore.sendMailLink;
  }

  mapLink(): SafeUrl {
    return this.dialogCore.openMapLink;
  }

  finishWaypoint(): void {
    console.log('Dialog finish waypoint')
    this.dialogCore.finishWaypoint();
  }
  cancelWaypoint(): void {
    this.dialogCore.cancelWaypoint();
  }
}
