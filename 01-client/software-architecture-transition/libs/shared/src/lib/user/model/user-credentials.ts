export interface UserCredentials {
  user: string;
  password: string;
  inspectorId?: number;
  jwtToken?: string;
  validUntil?: Date;
}
