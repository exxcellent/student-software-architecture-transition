import {Injectable} from '@angular/core';
import {DataProviderService, RequestResult, RestClient} from '../../../../shared/data-access';
import {UserCredentialsTO} from '../types/user-credentials.to';
import {JwtTokenTO} from '../types/jwt-token.to';
import {UserCredentials} from '../../../model/user-credentials';
import {fromResponse, toRequest} from '../mapper/user-credentials.mapper';
import {HttpErrorResponse} from '@angular/common/http';
import {RestResponse} from '../../../../shared/data-access/types/rest-response.interface';

@Injectable()
export class AuthenticationConnectorService extends DataProviderService{

  serviceSubUrl = 'v1/authenticate';

  constructor(private http: RestClient) {
    super()
  }

  authenticate(userCredentials: UserCredentials): Promise<RestResponse<UserCredentials>> {
    const request: UserCredentialsTO = toRequest(userCredentials);

    return new Promise((resolve, reject) => {

      this.http.POST<JwtTokenTO>(this.getUrl(), request).then((response: JwtTokenTO) => {

        const validUntil = new Date(Date.now());
        validUntil.setHours(validUntil.getHours() + 3);

        const userCredentialsResponse = fromResponse(request, response);
        userCredentialsResponse.validUntil = validUntil;
        userCredentialsResponse.inspectorId = userCredentials.inspectorId;

        resolve({ payload: userCredentialsResponse, result: RequestResult.SUCCESS });

      }, (error: HttpErrorResponse) => {
        if (error.status === 0) {
          reject({result: RequestResult.CONNECTION_PROBLEM});
        } else {
          reject({result: RequestResult.FAILURE});
        }
      });
    });
  }

}
