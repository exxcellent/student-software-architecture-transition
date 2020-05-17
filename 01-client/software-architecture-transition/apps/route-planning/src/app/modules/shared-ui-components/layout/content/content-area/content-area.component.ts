import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-content-area',
  template: `
    <div class="content-area">
      <div style="height: 100%">
        <ng-content style="height: 100%"></ng-content>
      </div>
    </div>
    `,
  styles: [
    `
      :host {
        height: 100%;
        display: block;
      }

      .content-area {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
      }

      .content-area div:first-child {
        width: 100%;
        max-width: 950px;

        max-width: var(--contentWidth);

        height: 100%;
      }
    `
  ]
})
export class ContentAreaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
