import {TransferObject} from '../../../../data-access';

export interface UserCredentialsTO extends TransferObject {
  userName: string,
  password: string
}
