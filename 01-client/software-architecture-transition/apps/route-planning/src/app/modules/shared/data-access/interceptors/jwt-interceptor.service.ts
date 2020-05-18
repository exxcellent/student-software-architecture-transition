import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AuthenticationRepositoryService} from '../../../user/data-access/authentication/authentication-repository.service';
import {exists} from '../../functions';

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationRepositoryService) {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    try {
      // do not add JWT for authentication requests
      if (request.url.indexOf('authenticate') > -1) {
        return next.handle(request);
      }

      if (!this.authService.isAuthenticated) {
        console.log('No authenticated user found. Authenticate...');

        this.authService.authenticate().then(userCredentials => {
          console.log('...authenticated');

          if (exists(userCredentials?.jwtToken)) {
              request = request.clone({
                setHeaders: {
                  Authorization: `Bearer ${userCredentials.jwtToken}`
                }
              });
            }

          return next.handle(request);
          });

      } else {

        // add authorization header with jwt token if available
        const jwt = this.authService.jwtToken;

        if (exists(jwt)) {
          request = request.clone({
            setHeaders: {
              Authorization: `Bearer ${jwt}`
            }
          });
        }

        return next.handle(request);
      }

    } catch (e) {
      // TODO correct error handling
      console.log('JWT token could not be append to http request header. Please login.');
    }
  }
}
