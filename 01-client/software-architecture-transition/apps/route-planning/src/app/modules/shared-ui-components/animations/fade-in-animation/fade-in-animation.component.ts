import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ui-fade-in-animation',
  template: `
    <div class="animation fade-in">
      <ng-content></ng-content>
    </div>
  `,
  styles: [`
    :host {
      height: 100%;
      display: block;
    }

    .animation.fade-in {
      -webkit-animation: fadein 3s;
      -moz-animation: fadein 3s;
      -ms-animation: fadein 3s;
      -o-animation: fadein 3s;
      animation: fadein 3s;
      height: 100%;
    }

    @keyframes fadein {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }

    /* Firefox < 16 */

    @-moz-keyframes fadein {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }

    /* Safari, Chrome and Opera > 12.1 */

    @-webkit-keyframes fadein {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }

    /* Internet Explorer */

    @-ms-keyframes fadein {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }

    /* Opera < 12.1 */

    @-o-keyframes fadein {
      from {
        opacity: 0;
      }

      to {
        opacity: 1;
      }
    }
  `]
})
export class FadeInAnimationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
