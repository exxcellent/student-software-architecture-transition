import {TransferObject} from '../../../../data-access';

export interface JwtTokenTO extends TransferObject {
  readonly jwtToken: string;
}
