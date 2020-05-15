import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'nav-tab-bar',
  template: `
   <nav class="tab-bar shadow-top-small">
     <a routerLink="/navigation/waypoints" routerLinkActive="active" class="tab" [ngClass]="activeClass('/navigation/waypoints')">
           <i class="material-icons">place</i>
     </a>
     <a routerLink="/navigation/map" routerLinkActive="active" class="tab"  [ngClass]="activeClass('/navigation/map')">
         <i class="material-icons">map</i>
     </a>
   </nav>
  `,
  styles: [
    `
      :host { display: block }
      .tab-bar {
        display: flex;
        justify-content: space-between;
        border-top: 1px solid rgba(0,0,0,.125);
        font-size: 1.5rem;
      }
      .tab {
        display: flex;
        width: 100%;
        align-items: center;
        justify-content: center;
        border-bottom: 3px solid rgba(0,0,0,0);
        padding: 1rem;
      }
      .tab:hover {
        background-color: var(--primary-light);
      }
      .active {
        border-bottom: 3px solid var(--primary);
      }
    `
  ],
})
export class TabBarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  activeClass(routerLink: string): string {
    //log('current url: ' + this.router.url + ', routerLink: ' + routerLink);
    return this.router.url === routerLink ? 'active': '';
  }
}
