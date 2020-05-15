import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-center-on-page',
  template: `
    <div class="center-on-page">
      <ng-content></ng-content>
    </div>
  `,
  styles: [`
    .center-on-page {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  `]
})
export class CenterOnPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
