import {Component} from '@angular/core';

@Component({
  selector: 'r-root',
  template: `
    <div with-theme class="application">
      <header>
        <div style="width: 100%; height: 100%; background-color: orangered">
          Header
        </div>
      </header>

      <main>
        <ui-page>
            <div style="width: 100%; height: 100%; background-color: #0060a9">
              Main
            </div>
        </ui-page>
      </main>

      <footer>
        <div style="width: 100%; height: 100%; background-color: lightblue">
          Footer
        </div>
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
    `,
  ],
})
export class AppComponent {
  title = 'route-planning';
}
