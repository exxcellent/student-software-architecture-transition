import {Component, OnInit} from '@angular/core';
import {CommonComponent} from '../../../shared-ui-components';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'nav-navigation-page',
  template: `
    <div id="navigation-page">

      <div>
      </div>

      <div style="flex: 1 1 100%">
        <router-outlet style="height: 100%;"></router-outlet>
      </div>

      <div *ngIf="isMobile">
        <nav-tab-bar></nav-tab-bar>
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
