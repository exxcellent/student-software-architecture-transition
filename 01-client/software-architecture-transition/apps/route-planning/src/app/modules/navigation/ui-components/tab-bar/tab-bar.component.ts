import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CommonComponent} from '@software-architecture-transition/shared-ui-components';
import {TabBarDialogCore} from '@software-architecture-transition/dialog-core/navigation';

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
      <a [routerLink]="'/navigation/details/' + activeWaypointId"
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

  activeWaypointId: number;

  constructor(private route: ActivatedRoute, private dialogCore: TabBarDialogCore) {
    super(route);
  }

  ngOnInit(): void {

    this.dialogCore.activeWaypointId$.subscribe(waypointId => {
        this.activeWaypointId = waypointId;
      });
  }
}
