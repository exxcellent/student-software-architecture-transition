import {Injectable} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {toISODateString} from '@software-architecture-transition/shared';
import {filter, flatMap, map} from 'rxjs/operators';
import {Route, Waypoint, WaypointStatus} from '@software-architecture-transition/dialog-core/navigation';
import {WaypointRepositoryService} from '@software-architecture-transition/data-access/navigation';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TabBarDialogCore {

  public activeWaypointId$ = new BehaviorSubject<number>(0);

  constructor(private route: ActivatedRoute, private waypointRepository: WaypointRepositoryService) {

    waypointRepository.currentDay$.subscribe((currentDay: Date) => {
      this.waypointRepository.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: Route) => route.waypoints),
        flatMap((waypoint) => waypoint),
        filter((waypoint: Waypoint) => waypoint.status === WaypointStatus.ACTIVE)
      ).subscribe(waypoint => {
        this.activeWaypointId$.next(waypoint.waypointId);
      })
    });
  }
}
