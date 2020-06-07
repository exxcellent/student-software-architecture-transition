import {Component, OnInit} from '@angular/core';
import {CommonComponent} from '../../../shared-ui-components';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'nav-navigation-page',
  template: `
    <div id="navigation-page">

      <div>
      </div>

      <div *ngIf="isMobile" style="flex: 1 1 100%">
        <router-outlet style="height: 100%;"></router-outlet>
      </div>

      <div *ngIf="isMobile">
        <nav-tab-bar></nav-tab-bar>
      </div>

      <div *ngIf="isDesktop" style="flex: 1 1 100%; overflow: hidden">
        <div class="waypoints shadow-bottom-small">
          <router-outlet style="height: 100%;"></router-outlet>
<!--          <nav-navigation-waypoints></nav-navigation-waypoints>-->
        </div>
        <div class="map"><nav-navigation-map></nav-navigation-map></div>
      </div>
    </div>
  `,
  styles: [
    `
      :host { display: block; min-height: 100%; height: 100% }
      #navigation-page {
        height: 100%;
        display: flex;
        flex-direction: column;
      }
      .waypoints {
        position: absolute;
        background-color: var(--background);
        z-index: 1000;
        height: calc(100% - 4.7rem);
        border-right: 1px solid rgba(0,0,0,.125);
        min-width: 300px;
        width: 33vw;
      }
      .map {
        height: 100%;
      }
    `
  ],
})
export class NavigationPage extends CommonComponent implements OnInit {

  constructor(private route: ActivatedRoute) {
    super(route)
  }

  ngOnInit(): void {
  }

}
