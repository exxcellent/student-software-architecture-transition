import {Injectable} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {toISODateString} from '@software-architecture-transition/shared';
import {filter, flatMap, map} from 'rxjs/operators';
import {NavigationDataAccess, RouteDTO, WaypointDTO, WaypointStatusDTO} from '../../dataaccess';
import {BehaviorSubject} from 'rxjs';

@Injectable()
export class TabBarDialogCore {

  public activeWaypointId$ = new BehaviorSubject<number>(0);

  constructor(private route: ActivatedRoute,
              private navigationDataAccess: NavigationDataAccess) {

    navigationDataAccess.currentDay$.subscribe((currentDay: Date) => {
      this.navigationDataAccess.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: RouteDTO) => route.waypoints),
        flatMap((waypoint) => waypoint),
        filter((waypoint: WaypointDTO) => waypoint.status === WaypointStatusDTO.ACTIVE)
      ).subscribe(waypoint => {
        this.activeWaypointId$.next(waypoint.waypointId);
      })
    });
  }
}
