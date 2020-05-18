import {TransferObject} from '../../../../shared/data-access';

export interface JwtTokenTO extends TransferObject {
  readonly jwtToken: string;
}
