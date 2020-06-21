import {Inject, Injectable} from '@angular/core';
import {NAVIGATION_DATA_ACCESS} from '@software-architecture-transition/data-access/navigation';
import {filter, flatMap, map} from 'rxjs/operators';
import {toISODateString} from '@software-architecture-transition/shared';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {NavigationDataAccess, RouteDTO, WaypointDTO, WaypointStatusDTO} from '../../dataaccess';


@Injectable({
  providedIn: 'root'
})
export class NavigationWaypointDetailsDialogCore {

  private _waypoint: WaypointDTO;
  private _waypointId: number;

  constructor(@Inject(NAVIGATION_DATA_ACCESS) private navigationDataAccess: NavigationDataAccess,
              private route: ActivatedRoute,
              private sanitizer: DomSanitizer) {
    this.route.paramMap.pipe(
      filter((paramMap: ParamMap) => paramMap.has('waypointId')),
      map((paramMap: ParamMap) => paramMap.get('waypointId')),
      map((waypointId: string) => Number.parseInt(waypointId, 10))
    ).subscribe((waypointId: number) => this._waypointId = waypointId);

    navigationDataAccess.currentDay$.subscribe((currentDay: Date) => {
     this.navigationDataAccess.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: RouteDTO) => route.waypoints),
        flatMap((waypoint) => waypoint),
        filter((waypoint: WaypointDTO) => waypoint.waypointId === this._waypointId)
      ).subscribe(waypoint => {
        this._waypoint = waypoint;
      })
    });
  }

  get waypoint(): WaypointDTO {
    return this._waypoint;
  }

  get callContactLink(): string {
   return 'tel:' + this._waypoint.contact.phoneNumber
  }
  get sendMailLink(): string {
    return 'mailto:' + this._waypoint.contact.email
  }
  get openMapLink(): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(`geo:${this._waypoint.location.lat},${this._waypoint.location.lng}`).toString();
  }
  get navigateToLink(): string {
    return `google.navigation:q=${this._waypoint.location.lat},${this._waypoint.location.lng}`;
  }
  get isPendingWaypoint(): boolean {
    return this._waypoint.status === WaypointStatusDTO.PENDING;
  }
  get isCurrentWaypoint(): boolean {
    return this._waypoint.status === WaypointStatusDTO.ACTIVE;
  }

  finishWaypoint(): void {
    this.navigationDataAccess.finishWaypoint(this._waypoint.waypointId, this._waypoint.version)
  }
  cancelWaypoint(): void {
    this.navigationDataAccess.cancelWaypoint(this._waypoint.waypointId, this._waypoint.version)
  }
}
