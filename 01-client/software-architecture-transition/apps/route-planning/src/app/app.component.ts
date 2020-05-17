import {Component} from '@angular/core';

@Component({
  selector: 'r-root',
  template: `
    <div with-theme class="application">
      <header>
        <r-header></r-header>
      </header>

      <main>
        <ui-page>
          <router-outlet style="height: 100%"></router-outlet>
        </ui-page>
      </main>

      <footer>
        <router-outlet name="footer"></router-outlet>
      </footer>
    </div>

  `,

  styles: [
    `
      .application {
        height: 100%;
        height: 100vh;
        display: grid;
        grid-template-rows: auto 1fr auto;
        grid-row-gap: 0;
      }

      main {
        height: 100%;
      }
    `,
  ],
})
export class AppComponent {
  title = 'route-planning';
}
