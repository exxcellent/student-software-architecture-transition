import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {CommonComponent} from '../../../shared-ui-components';
import {toISODateString} from '../../../shared/functions';
import {filter, flatMap, map} from 'rxjs/operators';
import {Route} from '../../model/route';
import {Waypoint} from '../../model/waypoint';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';
import {WaypointStatus} from '../../model/waypoint-status.enum';

@Component({
  selector: 'nav-tab-bar',
  template: `
    <nav class="tab-bar shadow-top-small">
      <!-- add a routerLink for each dialog -->
      <a routerLink="/navigation/waypoints"
         routerLinkActive="active"
         class="tab"
         [queryParams]="currentQueryParams()">
        <i class="material-icons">list</i>
      </a>
      <a [routerLink]="'/navigation/details/' + currentWaypointId"
         routerLinkActive="active"
         class="tab"
         [queryParams]="currentQueryParams()">
        <i class="material-icons">place</i>
      </a>
      <a routerLink="/navigation/map"
         routerLinkActive="active"
         class="tab"
         [queryParams]="currentQueryParams()">
        <i class="material-icons">map</i>
      </a>
    </nav>
  `,
  styles: [
      `
      :host {
        display: block;
        height: 100%;
      }

      .tab-bar {
        display: flex;
        justify-content: space-between;
        border-top: 1px solid rgba(0, 0, 0, .125);
        font-size: 1.5rem;
      }

      .tab {
        display: flex;
        width: 100%;
        align-items: center;
        justify-content: center;
        border-bottom: 3px solid rgba(0, 0, 0, 0);
        padding: 1rem;
      }

      .tab:hover {
        background-color: var(--primary-light);
      }

      .active {
        border-bottom: 3px solid var(--primary);
      }
    `
  ],
})
export class TabBarComponent extends CommonComponent implements OnInit {

  private pathWaypointId: number;
  private activeWaypointId: number;

  constructor(private router: Router, private route: ActivatedRoute, private waypointRepository: WaypointRepositoryService) {
    super(route);
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter((paramMap: ParamMap) => paramMap.has('waypointId')),
      map((paramMap: ParamMap) => paramMap.get('waypointId')),
      map((waypointId: string) => Number.parseInt(waypointId, 10))
    ).subscribe((waypointId: number) => this.pathWaypointId = waypointId);

    this.waypointRepository.currentDay$.subscribe((currentDay: Date) => {
      this.waypointRepository.routes$.pipe(
        map(routes => routes[toISODateString(currentDay)]?.myRoute),
        filter((route) => !!route),
        map((route: Route) => route.waypoints),
        flatMap((waypoint) => waypoint),
        filter((waypoint: Waypoint) => waypoint.status === WaypointStatus.ACTIVE)
      ).subscribe(waypoint => {
        this.activeWaypointId = waypoint.waypointId;
      })
    });
  }

  get currentWaypointId(): number {
    return this.activeWaypointId;
  }
}
