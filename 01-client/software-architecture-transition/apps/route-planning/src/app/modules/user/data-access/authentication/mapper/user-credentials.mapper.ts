import {UserCredentialsTO} from '../types/user-credentials.to';
import {UserCredentials} from '../../../model/user-credentials';
import {JwtTokenTO} from '../types/jwt-token.to';

export function fromResponse(request: UserCredentialsTO, response: JwtTokenTO): UserCredentials {
  return { user: request.userName, password: request.password, jwtToken: response.jwtToken };
}

export function toRequest(request: UserCredentials): UserCredentialsTO {
  return { userName: request.user, password: request.password };
}
