import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-col-layout',
  template: `
    <div class="col-layout">
      <ng-content></ng-content>
    </div>
  `,
  styles: [`
    .col-layout {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: row;
    }

    :host /deep/ * {
      flex-grow: 1;
    }`]
})
export class ColLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
