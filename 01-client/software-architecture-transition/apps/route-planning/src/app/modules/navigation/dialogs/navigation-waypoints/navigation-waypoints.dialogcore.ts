import {Injectable} from '@angular/core';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';
import {BehaviorSubject, Observable, Subscription} from 'rxjs';
import {Route} from '../../model/route';
import {Waypoint} from '../../model/waypoint';
import {WaypointWithIcon} from './types/waypoint-with-icon.interface';
import {WAYPOINT_ICONS} from '../waypoint-icons';
import {filter, map} from 'rxjs/operators';
import {toISODateString} from '../../../shared/functions';
import {WaypointStatus} from '../../model/waypoint-status.enum';
import {ActivatedRoute, Router} from '@angular/router';

@Injectable()
export class NavigationWaypointsDialogCore {

  public currentWaypointsWithIcons$: Observable<WaypointWithIcon[]>;
  private _currentRoute: Route;
  public currentWaypointsWithIconsSubject$ = new BehaviorSubject<WaypointWithIcon[]>([]);

  private _currentRouteSubscription$: Subscription;

  constructor(private waypointRepository: WaypointRepositoryService, private router: Router, private route: ActivatedRoute) {
    waypointRepository.currentDay$.subscribe((currentDay: Date) => {

      if (this._currentRouteSubscription$) {
        this._currentRouteSubscription$.unsubscribe();
      }

      this.currentWaypointsWithIcons$ = this.waypointRepository.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: Route) => {
          const sortedWaypoints = Object.assign([], route.waypoints);
          sortedWaypoints.sort((a, b) => a.orderIndex - b.orderIndex);

          const waypointsWithIcon: WaypointWithIcon[] = [];
          sortedWaypoints.forEach((wp: Waypoint, index: number) => {
            waypointsWithIcon.push({waypoint: wp, iconUrl: WAYPOINT_ICONS.appointmentIcons[index], currentWaypoint: wp.status === WaypointStatus.ACTIVE})
          });

          return waypointsWithIcon;
        })
      );

      this._currentRouteSubscription$ = this.currentWaypointsWithIcons$.subscribe(waypoints => {
        this.currentWaypointsWithIconsSubject$.next(waypoints);
      })
    });




  }

  isNextWaypoint(waypoint: Waypoint): boolean {
    return waypoint.status === WaypointStatus.ACTIVE;
  }

  openDetails(waypoint: Waypoint): void {
    this.router.navigate(['navigation','details', waypoint.waypointId]);

  }
}
