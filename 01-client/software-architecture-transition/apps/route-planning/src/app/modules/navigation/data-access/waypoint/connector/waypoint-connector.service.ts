import {Injectable} from '@angular/core';
import {ConnectionError, DataProviderService, RestClient} from '../../../../shared/data-access';
import {Route} from '../../../model/route';
import {RouteCTO} from '../types/route.cto';
import {fromResponse} from '../mapper/route.mapper';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {actions, AppState} from '../../../../../data-access/app/state/app';
import {Store} from '@ngrx/store';

@Injectable({
  providedIn: 'root'
})
export class WaypointConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}';

  constructor(private http: RestClient, private appStore: Store<AppState>) {
    super()
  }

  findRoute(date: Date, inspectorId: number): Observable<Route>{

    return this.http.GET<RouteCTO>(this.getDailyWaypointUrl(date, inspectorId)).pipe(
      map((response: RouteCTO) => {
        return fromResponse(response);
    }),
      catchError((error: ConnectionError) => {
        console.error('Waypoint Connector: Dispatch show notification');
        this.appStore.dispatch(actions.showNotification({data: error.asNotification }));

        return throwError(error)
      }));
  }

  private getDailyWaypointUrl(date: Date, inspectorId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = this.getUrl().replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`)

    return url;
  }

}
