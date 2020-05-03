package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationTO extends BusinessTO {

  private Long notificationId;
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
