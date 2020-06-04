package de.exxcellent.student.softwarearchitecture.transition.planning.route.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.route.api.types.notification.NotificationChannel;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.api.types.notification.NotificationDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.data.entities.notification.Channel;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.data.entities.notification.NotificationEntity;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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
    notificationDO.setArrivalIn(Duration.of(notification.getArrivalIn(), ChronoUnit.SECONDS));

    return notificationDO;
  };

  public static final Function<NotificationDO, NotificationEntity> toNotificationEntity = notification -> {
    var notificationEntity = new NotificationEntity();

    notificationEntity.setId(notification.getNotificationId());
    notificationEntity.setVersion(notification.getVersion());
    notificationEntity.setChannel(NotificationMapper.fromNotificationChannel.apply(notification.getNotificationChannel()));
    notificationEntity.setNotifiedAt(notification.getNotifiedAtUtc().atOffset(ZoneOffset.UTC));
    notificationEntity.setArrivalIn(notification.getArrivalIn().toSeconds());

    return notificationEntity;
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

  private static final Function<NotificationChannel, Channel> fromNotificationChannel = channel -> {
    switch (channel) {
      case EMAIL: return Channel.EMAIL;
      case PHONE: return Channel.PHONE;
      case SMS: return Channel.SMS;
      case AUTOMATIC:
      default:
        return Channel.AUTOMATIC;
    }
  };
}
