import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'ui-centered-layout',
  template: `
    <ui-col-layout>
      <div></div>
      <div class="centered-content"
           [ngStyle]="getMaxWidth()">
        <ng-content></ng-content>
      </div>
      <div></div>
    </ui-col-layout>
  `,
  styles: [`
      .centered-content {
        flex-grow: 2;
        min-width: 60%;
      }`
  ]
})
export class CenteredLayoutComponent implements OnInit {
  @Input() width = 60;

  constructor() { }

  ngOnInit(): void {
  }

  getMaxWidth(): object {
    return JSON.parse(`{"min-width":"${this.width}", "max-width":"${this.width}%"}`);
  }
}
