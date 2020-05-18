import {Component, OnInit} from '@angular/core';
import {NavigationMapDialogCore} from './navigation-map.dialogcore';

// @ts-ignore

@Component({
  selector: 'nav-navigation-map',
  templateUrl: './navigation-map.component.html',
  styleUrls: ['./navigation-map.component.css'],
  providers: [NavigationMapDialogCore]
})
export class NavigationMapComponent implements OnInit {

  constructor(private dialogCore: NavigationMapDialogCore) { }

  ngOnInit(): void {

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

  isMapTypeHybrid(): boolean {
    return this.dialogCore.mapType === 'hybrid';
  }

  hasActiveClass(isActive: boolean): string {
    return isActive ? 'active' : '';
  }
}
