import {Inject, Injectable} from '@angular/core';
import {NAVIGATION_DATA_ACCESS} from '@software-architecture-transition/data-access/navigation';
import {BehaviorSubject, Observable, Subscription} from 'rxjs';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {WAYPOINT_ICONS} from '../waypoint-icons';
import {filter, map} from 'rxjs/operators';
import {toISODateString} from '@software-architecture-transition/shared';
import {ActivatedRoute, Router} from '@angular/router';
import {NavigationDataAccess, RouteDTO, WaypointDTO, WaypointStatusDTO} from '../../dataaccess';


@Injectable()
export class NavigationWaypointsDialogCore {

  public currentWaypointsWithIcons$: Observable<WaypointWithIcon[]>;
  private _currentRoute: RouteDTO;
  public currentWaypointsWithIconsSubject$ = new BehaviorSubject<WaypointWithIcon[]>([]);

  private _currentRouteSubscription$: Subscription;

  constructor(@Inject(NAVIGATION_DATA_ACCESS) private navigationDataAccess: NavigationDataAccess,
              private router: Router,
              private route: ActivatedRoute) {
    navigationDataAccess.currentDay$.subscribe((currentDay: Date) => {

      if (this._currentRouteSubscription$) {
        this._currentRouteSubscription$.unsubscribe();
      }

      this.currentWaypointsWithIcons$ = this.navigationDataAccess.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: RouteDTO) => {
          const sortedWaypoints = Object.assign([], route.waypoints);
          sortedWaypoints.sort((a, b) => a.orderIndex - b.orderIndex);

          const waypointsWithIcon: WaypointWithIcon[] = [];
          sortedWaypoints.forEach((wp: WaypointDTO, index: number) => {
            waypointsWithIcon.push({waypoint: wp, iconUrl: WAYPOINT_ICONS.appointmentIcons[index], currentWaypoint: wp.status === WaypointStatusDTO.ACTIVE})
          });

          return waypointsWithIcon;
        })
      );

      this._currentRouteSubscription$ = this.currentWaypointsWithIcons$.subscribe(waypoints => {
        this.currentWaypointsWithIconsSubject$.next(waypoints);
      })
    });




  }

  isNextWaypoint(waypoint: WaypointDTO): boolean {
    return waypoint.status === WaypointStatusDTO.ACTIVE;
  }

  openDetails(waypoint: WaypointDTO): void {
    this.router.navigate(['navigation','details', waypoint.waypointId]);

  }
}
