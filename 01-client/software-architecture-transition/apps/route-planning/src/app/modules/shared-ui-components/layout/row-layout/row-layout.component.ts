import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-row-layout',
  template: `
    <div class="row-layout">
      <ng-content></ng-content>
    </div>
  `,
  styles: [`
    .row-layout {
      height: 100%;
      width: 100%;
      display: flex;
      flex-direction: column;
    }
  `]
})
export class RowLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
