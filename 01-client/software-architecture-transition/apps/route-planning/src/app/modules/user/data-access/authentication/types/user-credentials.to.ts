import {TransferObject} from '../../../../shared/data-access';

export interface UserCredentialsTO extends TransferObject {
  userName: string,
  password: string
}
