import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-grid-layout',
  template: `
    <div class="grid-layout">
      <ng-content></ng-content>
    </div>
  `,
  styles: [`
    .grid-layout {
      height: 100%;
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      flex-wrap: wrap;
    }
  `]
})
export class GridLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
