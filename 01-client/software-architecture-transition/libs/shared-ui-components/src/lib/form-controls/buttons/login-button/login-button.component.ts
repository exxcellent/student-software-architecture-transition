import {Component, OnInit} from '@angular/core';
import {AuthenticationRepositoryService, log, UserCredentials} from '@software-architecture-transition/shared';

@Component({
  selector: 'r-login-button',
  template: `
    <div *ngIf="!isAuthenticated">
      <ui-button
              (click)="login()"
              [disabled]="loading">Login</ui-button>
    </div>

    <div *ngIf="isAuthenticated">
      <ui-button [disabled]="true"><i class="material-icons">person</i> {{ userCredentials.user }}</ui-button>
    </div>
  `,
  styles: [
  ]
})
export class LoginButtonComponent implements OnInit {

  loading = false;
  authenticated = false;
  userCredentials: UserCredentials;

  constructor(private authService: AuthenticationRepositoryService) { }

  ngOnInit(): void {
    this.authService.userCredentials$.subscribe((userCredentials: UserCredentials) => {
      this.userCredentials = userCredentials;
    });

    this.authService.userCredentialsLoading$.subscribe(loading => {
      log('Login: isLoading = ' + loading);

      this.loading = loading;
    });

    this.authService.userAuthenticated$.subscribe(authenticated => {
      this.authenticated = authenticated;
    });
  }

  login(): void {
    this.authService.authenticate();
  }

  get isAuthenticated(): boolean {
    return this.authenticated;
  }
}
