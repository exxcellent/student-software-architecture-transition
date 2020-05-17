import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-page',
  template: `
    <ui-fade-in-animation>
      <ui-content-area>
        <ng-content></ng-content>
      </ui-content-area>
    </ui-fade-in-animation>
  `,
  styles: [
    `
      :host {
        min-height: 100%
        display: block;
      }
    `
  ]
})
export class PageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
