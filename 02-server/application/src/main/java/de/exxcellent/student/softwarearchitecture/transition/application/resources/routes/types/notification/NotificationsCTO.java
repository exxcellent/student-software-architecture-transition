package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationsCTO implements ReadOnlyTO {

  private List<NotificationTO> notifications;

  public NotificationsCTO() {
  }

  public List<NotificationTO> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<NotificationTO> notifications) {
    this.notifications = notifications;
  }
}
