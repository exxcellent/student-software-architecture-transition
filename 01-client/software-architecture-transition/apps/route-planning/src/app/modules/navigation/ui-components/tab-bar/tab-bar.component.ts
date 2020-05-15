import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonComponent} from '../../../shared-ui-components';

@Component({
  selector: 'nav-tab-bar',
  template: `
    <nav class="tab-bar shadow-top-small">
      <!-- add a routerLink for each dialog -->
      <a routerLink="/navigation/waypoints"
         routerLinkActive="active"
         class="tab"
         [queryParams]="currentQueryParams()"
         [ngClass]="activeClass('/navigation/waypoints')">
        <i class="material-icons">place</i>
      </a>
      <a routerLink="/navigation/map"
         routerLinkActive="active"
         class="tab"
         [queryParams]="currentQueryParams()"
         [ngClass]="activeClass('/navigation/map')">
        <i class="material-icons">map</i>
      </a>
    </nav>
  `,
  styles: [
      `
      :host {
        display: block
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

  constructor(private router: Router, private route: ActivatedRoute) {
    super(route);
  }

  ngOnInit(): void {

  }

  activeClass(routerLink: string): string {
    //log('current url: ' + this.router.url + ', routerLink: ' + routerLink);
    return this.router.url === routerLink ? 'active' : '';
  }
}
