import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'nav-navigation-page',
  template: `
    <div id="navigation-page">
      Navigation

      <router-outlet></router-outlet>
    </div>
  `,
  styles: [
    `
      :host { display: block; }
      #navigation-page {

      }
    `
  ],
})
export class NavigationPage implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
