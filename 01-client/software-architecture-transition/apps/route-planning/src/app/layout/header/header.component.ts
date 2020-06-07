import {Component, OnInit} from '@angular/core';
import {CommonComponent} from '../../modules/shared-ui-components';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'r-header',
  template: `
    <div class="top-bar shadow-bottom-small">
        <h1 class="title"><i class="material-icons">near_me</i> Routenplaner</h1>

        <r-login-button></r-login-button>
    </div>
  `,
  styles: [
    `
      .top-bar {
        /*position: fixed;*/
        /*top: 0;*/
        /*z-index: 1000;*/
        width: 100%;
        height: 4.7rem;
        padding: .3rem 1.5rem;
        display: -webkit-box;
        display: flex;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
        flex-flow: row nowrap;
        border-bottom: 1px solid rgba(0,0,0,.125);
        justify-content: space-between;
        align-items: baseline;
      }

      .title {
        font-size: 1.3rem;
        user-focus: none;
        cursor: default;
      }
    `
  ],
})
export class HeaderComponent extends CommonComponent implements OnInit {

  constructor(private route: ActivatedRoute) {
    super(route);
  }

  ngOnInit(): void {
  }

}
