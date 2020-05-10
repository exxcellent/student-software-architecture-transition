package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.mapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.notification.Channel;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.notification.NotificationEntity;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class NotificationMapper {

  private NotificationMapper() {
  }

  public static final Function<NotificationEntity, NotificationDO> toNotificationDO = notification -> {
    var notificationDO = new NotificationDO();

    notificationDO.setNotificationId(notification.getId());
    notificationDO.setVersion(notification.getVersion());
    notificationDO.setWaypointId(notification.getWaypoint().getId());
    notificationDO.setNotificationChannel(NotificationMapper.toNotificationChannel.apply(notification.getChannel()));
    notificationDO.setNotifiedAtUtc(notification.getNotifiedAt().toInstant());
    notificationDO.setArrivalIn(notification.getArrivalIn());


    return notificationDO;
  };

  private static final Function<Channel, NotificationChannel> toNotificationChannel = channel -> {
    switch (channel) {
      case EMAIL: return NotificationChannel.EMAIL;
      case PHONE: return NotificationChannel.PHONE;
      case SMS: return NotificationChannel.SMS;
      case AUTOMATIC:
      default:
        return NotificationChannel.AUTOMATIC;
    }
  };
}
