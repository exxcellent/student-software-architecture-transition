export interface NotificationTO {
  readonly notificationId?: number;
  readonly version?: number;
  readonly waypointId: number;
  readonly channel: string;
  readonly notifiedAt: string;
  readonly arrivalTimeInSeconds?: number
}
