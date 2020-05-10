package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.mapper;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationChannel;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationDO;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class NotificationMapper {

  private final static String DATE_FORMAT = "yyyy-MM-ddZ";
  private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

  private NotificationMapper() {}

  public static Function<List<NotificationDO>, NotificationsCTO> toNotificationsCTO = notifications -> {
    var notificationsCTO = new NotificationsCTO();

    notificationsCTO.setNotifications(notifications.stream()
        .map(NotificationMapper.toNotificationTO)
        .collect(Collectors.toList()));

    return notificationsCTO;
  };

  public static Function<NotificationDO, NotificationTO> toNotificationTO = notification -> {
    var notificationTO = new NotificationTO();

    notificationTO.setNotificationId(notification.getNotificationId());
    notificationTO.setWaypointId(notification.getWaypointId());
    notificationTO.setVersion(notification.getVersion());
    notificationTO.setChannel(NotificationMapper.toChannel.apply(notification.getNotificationChannel()));
    notificationTO.setNotifiedAt(notification.getNotifiedAtUtc().toString());
    notificationTO.setArrivalTimeInSeconds(notification.getArrivalIn().toSeconds());

    return notificationTO;
  };

  public static Function<NotificationTO, NotificationDO> toNotificationDO = notification -> {
    var notificationDO = new NotificationDO();

    notificationDO.setNotificationId(notification.getNotificationId());
    notificationDO.setVersion(notification.getVersion());
    notificationDO.setWaypointId(notification.getWaypointId());
    notificationDO.setNotificationChannel(NotificationMapper.fromChannel.apply(notification.getChannel()));
    notificationDO.setNotifiedAtUtc(Instant.parse(notification.getNotifiedAt()));
    notificationDO.setArrivalIn(Duration.of(notification.getArrivalTimeInSeconds(), ChronoUnit.SECONDS));

    return notificationDO;
  };


  private static final Function<de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel,
      NotificationChannel>
      toChannel = channel -> {
    switch (channel) {
      case SMS: return NotificationChannel.SMS;
      case PHONE: return NotificationChannel.PHONE;
      case EMAIL: return NotificationChannel.EMAIL;
      case AUTOMATIC:
      default:
        return NotificationChannel.AUTOMATIC;
    }
  };

  private static final Function<NotificationChannel, de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel>
      fromChannel = channel -> {
    switch (channel) {
      case SMS: return de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel.SMS;
      case PHONE: return de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel.PHONE;
      case EMAIL: return de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel.EMAIL;
      case AUTOMATIC:
      default:
        return de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationChannel.AUTOMATIC;
    }
  };
}
