import {Injectable} from '@angular/core';
import {DataProviderService, RestClient} from '../../../../shared/data-access';
import {Route} from '../../../model/route';
import {RouteCTO} from '../types/route.cto';
import {fromResponse} from '../mapper/route.mapper';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WaypointConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/routes/{date}/inspectors/{inspectorId}';

  constructor(private http: RestClient) {
    super()
  }

  findRoute(date: Date, inspectorId: number): Observable<Route>{
    return this.http.GET<RouteCTO>(this.getDailyWaypointUrl(date, inspectorId)).pipe(
      map((response: RouteCTO) => {
        return fromResponse(response);
    }));
  }

  private getDailyWaypointUrl(date: Date, inspectorId: number): string {
    const dateString = date.toISOString().split('T')[0];

    let url = this.getUrl().replace("{date}", `${dateString}`);
    url = url.replace("{inspectorId}", `${inspectorId}`)

    return url;
  }

}
