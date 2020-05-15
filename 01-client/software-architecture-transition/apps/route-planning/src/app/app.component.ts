import {Component} from '@angular/core';

@Component({
  selector: 'r-root',
  template: `
    <header class="flex">
      <h1>Welcome to {{ title }}!</h1>
    </header>
    <main class="flex">

    </main>
  `,

  styles: [
    `
      :host {
        display: block;
        margin: 5vw auto;
      }

      .flex {
        display: flex;
        align-items: center;
        justify-content: center;
      }

      header {

      }

      main {

      }

      footer {

      }
    `,
  ],
})
export class AppComponent {
  title = 'route-planning';
}
