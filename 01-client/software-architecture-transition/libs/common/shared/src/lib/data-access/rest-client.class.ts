import {Injectable} from '@angular/core';

import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {TransferObject} from './types/transfer-object.interface';
import {ConnectionError} from './types/connection-error.class';

const httpOptions = {
  headers: new HttpHeaders({
    // 'demo header':  'demo header value',
  })
};

@Injectable({
  providedIn: 'root'
})
export class RestClient {
  constructor(private http: HttpClient) {
  }

  /**
   * Perform a GET request to receive a response of the given type T
   *
   * @param url of the REST resource
   * @constructor type of response T
   */
  public GET<T>(url: string): Observable<T> {
    console.log('Send GET request to ' + url);

    return this.http.get<T>(url, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Perform a POST request to create a new instance of the given payload and
   * receive a response of the given type T
   *
   * @param url of the REST resource
   * @param payload
   * @constructor type of response T
   */
  public POST<T>(url: string, payload: TransferObject): Observable<T> {
    console.log('Send POST request to ' + url + ' with payload ' + JSON.stringify(payload));

    return this.http.post<T>(url, payload, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Perform a PUT request to update an existing instance of the given payload and
   * receive a response of the given type T
   *
   * @param url of the REST resource
   * @param payload
   * @constructor type of response T
   */
  public PUT<T>(url: string, payload: TransferObject): Observable<T> {
    console.log('Send PUT request to ' + url + ' with payload ' + JSON.stringify(payload));

    return this.http.put<T>(url, payload, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Perform a DELETE request to delete an existing instance with a given id with the url
   *
   * @param url of the REST resource
   * @constructor type of response T
   */
  public DELETE<T>(url: string): Observable<T> {
    console.log('Send DELETE request to ' + url);

    return this.http.delete<T>(url, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  public DOWNLOAD(url: string): Promise<Blob> {

    const objectUrl: string = null;

    console.log('Download: Send GET request to ' + url);

    return this.http.get(url, {responseType: 'blob'})
               .pipe(
                 catchError(this.handleError)
               ).toPromise();
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);

      return throwError(new ConnectionError(0));

    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.warn(
        `Backend returned status code ${error.status}, ` +
        `body was: ${JSON.stringify(error.error)}`);

      return throwError(new ConnectionError(error.status, error.message, error.url));
    }
  }
}
