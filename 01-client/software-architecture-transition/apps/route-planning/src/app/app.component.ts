import {Component} from '@angular/core';
import {NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router, RouterEvent} from '@angular/router';
import {Store} from '@ngrx/store';
import {actions, AppState, selectors} from './data-access/app/state/app';
import {NotificationLevel, NotificationMessage} from './modules/shared/types';
import {exists} from './modules/shared/functions';

@Component({
  selector: 'r-root',
  template: `
    <div with-theme class="application">
      <header style="z-index: 5;">
        <r-header></r-header>
      </header>

      <main>
        <ui-page *ngIf="!loading && !hasNotification">
          <router-outlet style="height: 100%"></router-outlet>
        </ui-page>
        <ui-page *ngIf="loading && !hasNotification">
          <ui-center-on-page>
            <ui-rotate-animation style="font-size: 3rem">
              <i class="material-icons">autorenew</i>

            </ui-rotate-animation>
          </ui-center-on-page>
        </ui-page>
        <ui-page *ngIf="hasNotification">
          <ui-center-on-page>
            <div style="background-color: var(--background-dark); border: 1px solid var(--text-light); padding: 0 1rem 1rem 1rem; border-radius: 2px">
              <h3>
                <span style="color: var(--primary)" *ngIf="notification.level === NotificationLevel.INFO"><i class="material-icons">info</i> Hinweis</span>
                <span style="color: var(--warn)" *ngIf="notification.level === NotificationLevel.WARN"><i class="material-icons">warning</i> Warnung</span>
                <span style="color: var(--error)" *ngIf="notification.level === NotificationLevel.ERROR"><i class="material-icons">error</i> Fehler</span>
              </h3>
              <p>
              {{ notification.message }}
              </p>
            </div>
          </ui-center-on-page>
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
  loading = false;
  notification: NotificationMessage;
  NotificationLevel = NotificationLevel;

  constructor(private router: Router, private store: Store<AppState>) {
    // show/ hide loading indicator in navigation events
    router.events.subscribe((event: RouterEvent) => {
      this.navigationInterceptor(event);
    });

    store.select(selectors.selectPageLoading).subscribe(loading => {
      this.loading = loading;
    });

    store.select(selectors.selectNotification).subscribe(notification => {
      this.notification = notification;
    });
  }

  // Shows and hides the loading spinner during RouterEvent changes
  private navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.store.dispatch(actions.showPageLoading());
    }
    if (event instanceof NavigationEnd) {
      this.store.dispatch(actions.hidePageLoading());
    }

    // Set loading state to false in both of the below events to hide the spinner in case a request fails
    if (event instanceof NavigationCancel) {
      this.store.dispatch(actions.hidePageLoading());
    }
    if (event instanceof NavigationError) {
      this.store.dispatch(actions.hidePageLoading());
    }
  }

  get hasNotification(): boolean {
    return exists(this.notification);
  }

}
