package de.exxcellent.student.softwarearchitecture.transition.planning.route.api.types.notification;

import de.exxcellent.student.softwarearchitecture.transition.common.types.BusinessDO;

import java.time.Duration;
import java.time.Instant;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationDO extends BusinessDO {

  private Long notificationId;
  private Long waypointId;
  private Instant notifiedAtUtc;
  private NotificationChannel notificationChannel;
  private Duration arrivalIn;

  public NotificationDO() {
  }

  public Long getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }

  public Long getWaypointId() {
    return waypointId;
  }

  public void setWaypointId(Long waypointId) {
    this.waypointId = waypointId;
  }

  public Instant getNotifiedAtUtc() {
    return notifiedAtUtc;
  }

  public void setNotifiedAtUtc(Instant notifiedAtUtc) {
    this.notifiedAtUtc = notifiedAtUtc;
  }

  public NotificationChannel getNotificationChannel() {
    return notificationChannel;
  }

  public void setNotificationChannel(NotificationChannel notificationChannel) {
    this.notificationChannel = notificationChannel;
  }

  public Duration getArrivalIn() {
    return arrivalIn;
  }

  public void setArrivalIn(Duration arrivalIn) {
    this.arrivalIn = arrivalIn;
  }
}
