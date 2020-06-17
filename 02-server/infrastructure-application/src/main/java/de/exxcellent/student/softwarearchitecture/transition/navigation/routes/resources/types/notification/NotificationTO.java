package de.exxcellent.student.softwarearchitecture.transition.navigation.routes.resources.types.notification;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationTO extends BusinessTO {

  private Long notificationId;
  private Long waypointId;
  private NotificationChannel channel;
  private String notifiedAt;
  private Long arrivalTimeInSeconds;

  public NotificationTO() {
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

  public NotificationChannel getChannel() {
    return channel;
  }

  public void setChannel(NotificationChannel channel) {
    this.channel = channel;
  }

  public String getNotifiedAt() {
    return notifiedAt;
  }

  public void setNotifiedAt(String notifiedAt) {
    this.notifiedAt = notifiedAt;
  }

  public Long getArrivalTimeInSeconds() {
    return arrivalTimeInSeconds;
  }

  public void setArrivalTimeInSeconds(Long arrivalTimeInSeconds) {
    this.arrivalTimeInSeconds = arrivalTimeInSeconds;
  }
}
