import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'nav-navigation-page',
  template: `
    <div id="navigation-page">

      <router-outlet style="height: 100%;"></router-outlet>

    </div>
  `,
  styles: [
    `
      :host { display: block; min-height: 100%; height: 100% }
      #navigation-page {
        height: 100%;
      }
    `
  ],
})
export class NavigationPage implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
