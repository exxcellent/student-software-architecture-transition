import {Component, OnInit} from '@angular/core';
import {SafeUrl} from '@angular/platform-browser';
import {
  NavigationWaypointDetailsDialogCore,
  WaypointCategoryDTO,
  WaypointDTO,
  WaypointStatusDTO
} from '@software-architecture-transition/dialog-core/navigation';


@Component({
  selector: 'nav-navigation-waypoint-details',
  templateUrl: './navigation-waypoint-details.component.html',
  styleUrls: ['./navigation-waypoint-details.component.css'],
  providers: [NavigationWaypointDetailsDialogCore]
})
export class NavigationWaypointDetailsComponent implements OnInit {

  WaypointCategory = WaypointCategoryDTO;
  WaypointStatus = WaypointStatusDTO;

  constructor(private dialogCore: NavigationWaypointDetailsDialogCore) { }

  ngOnInit(): void {

  }

  get isCurrentWaypoint(): boolean {
    return this.dialogCore.isCurrentWaypoint;
  }

  get isPendingWaypoint(): boolean {
    return this.dialogCore.isPendingWaypoint;
  }

  get waypoint(): WaypointDTO {
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
