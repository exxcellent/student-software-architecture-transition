import {Component, OnInit} from '@angular/core';
import {NavigationMapDialogCore, Waypoint} from '@software-architecture-transition/dialog-core/navigation';

// @ts-ignore

@Component({
  selector: 'nav-navigation-map',
  templateUrl: './navigation-map.component.html',
  styleUrls: ['./navigation-map.component.css'],
  providers: [NavigationMapDialogCore]
})
export class NavigationMapComponent implements OnInit {

  currentWayPoint: Waypoint;

  constructor(private dialogCore: NavigationMapDialogCore) { }

  ngOnInit(): void {
    this.dialogCore.currentWaypoint$.subscribe(waypoint => {
      this.currentWayPoint = waypoint;
    })
  }

  get center() {
    return this.dialogCore.mapCenter;
  }

  get options() {
    return this.dialogCore.mapOptions;
  }

  get boundary() {
    return this.dialogCore.mapBoundary;
  }

  get type(): string {
    return this.dialogCore.mapType;
  }

  get zoom(): number {
    return this.dialogCore.mapZoom;
  }

  renderRoute(map: google.maps.Map): void {
    this.dialogCore.renderRoute(map);
  }

  zoomChanged(zoomLevel: number): void {
    this.dialogCore.mapZoom = zoomLevel;
  }

  zoomIn(): void {
    this.dialogCore.zoomIn();
  }

  zoomOut(): void {
    this.dialogCore.zoomOut();
  }

  toggleMapType(): void {
    this.dialogCore.toggleMapType();
  }

  callContact(): void {
    this.dialogCore.callContact();
  }

  sendMail(): void {
    this.dialogCore.sendMail();
  }

  navigateToCurrentWaypoint(): void {
    this.dialogCore.navigateTo();
  }

  isMapTypeHybrid(): boolean {
    return this.dialogCore.mapType === 'hybrid';
  }

  hasActiveClass(isActive: boolean): string {
    return isActive ? 'active' : '';
  }
}
