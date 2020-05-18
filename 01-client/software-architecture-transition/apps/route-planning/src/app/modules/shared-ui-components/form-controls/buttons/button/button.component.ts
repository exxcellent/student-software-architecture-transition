import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'ui-button',
  template: `
    <button [disabled]="disabled || loading" (click)="onClick($event)">
      <ng-content></ng-content>
    </button>
  `,
  styles: [
    `
        :host {
          display: block;
          height: 100%;
        }

      button {
        border: 1px solid var(--text-light);
        border-radius: 2px;
        background-color: var(--background);
        padding: .5rem .8rem;
        color: var(--text);
      }

      button:hover, button:focus {
        background-color: var(--primary-light);
        border: 1px solid var(--primary);
        color: var(--primary);
      }

      button:disabled,  button:disabled:hover, button:disabled:focus  {
        border: 1px solid var(--text-light);
        color: var(--text-light);
        background-color: var(--background);
        user-focus: none;
        cursor: not-allowed;
      }
    `
  ]
})
export class ButtonComponent implements OnInit {

  @Input() disabled = false;
  @Input() loading = false;
  @Output() click = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  onClick($event: any): void {
    this.click.emit($event);
  }

}
