package de.exxcellent.student.softwarearchitecture.transition.component.route.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.notification.NotificationChannel;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.notification.NotificationDO;
import de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess.types.notification.Channel;
import de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess.types.notification.NotificationDTO;

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

  public static final Function<NotificationDTO, NotificationDO> toNotificationDO = notification -> {
    var notificationDO = new NotificationDO();

    notificationDO.setNotificationId(notification.getId());
    notificationDO.setVersion(notification.getVersion());
    notificationDO.setWaypointId(notification.getWaypoint().getId());
    notificationDO.setNotificationChannel(NotificationMapper.toNotificationChannel.apply(notification.getChannel()));
    notificationDO.setNotifiedAtUtc(notification.getNotifiedAt().toInstant());
    notificationDO.setArrivalIn(Duration.of(notification.getArrivalIn(), ChronoUnit.SECONDS));

    return notificationDO;
  };

  public static final Function<NotificationDO, NotificationDTO> toNotificationEntity = notification -> {
    var NotificationDTO = new NotificationDTO();

    NotificationDTO.setId(notification.getNotificationId());
    NotificationDTO.setVersion(notification.getVersion());
    NotificationDTO.setChannel(NotificationMapper.fromNotificationChannel.apply(notification.getNotificationChannel()));
    NotificationDTO.setNotifiedAt(notification.getNotifiedAtUtc().atOffset(ZoneOffset.UTC));

    if (notification.getArrivalIn() != null) {
      NotificationDTO.setArrivalIn(notification.getArrivalIn().toSeconds());
    }

    return NotificationDTO;
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
