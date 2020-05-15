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
  styles: []
})
export class PageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
