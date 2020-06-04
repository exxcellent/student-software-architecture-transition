package de.exxcellent.student.softwarearchitecture.transition.planning.route.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.api.NotificationComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.api.types.notification.NotificationDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.logic.NotificationLogic;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class NotificationFacade implements NotificationComponent {

  private final NotificationLogic notificationLogic;

  @Autowired
  public NotificationFacade(NotificationLogic notificationLogic) {
    this.notificationLogic = notificationLogic;
  }


  @Override
  public List<NotificationDO> findAllNotificationsByDateAndInspector(LocalDate date, Long inspectorId) {
    Preconditions.checkNotNull(date, "Date must not be null");
    Preconditions.checkNotNull(inspectorId, "InspectorId must not be null");
    Preconditions.checkArgument(inspectorId > 0, "InspectorId must be positive");

    var notificationEntities = notificationLogic.findAllByDateAndInspectorId(date, inspectorId);

    return notificationEntities.stream()
        .map(NotificationMapper.toNotificationDO)
        .collect(Collectors.toList());
  }


  @Override
  public List<NotificationDO> findAllNotificationsByWaypointId(Long waypointId) {
    Preconditions.checkNotNull(waypointId, "WaypointId must not be null");
    Preconditions.checkArgument(waypointId > 0, "WaypointId must be positive");

    var notificationEntities = notificationLogic.findAllByWaypointId(waypointId);

    return notificationEntities.stream()
        .map(NotificationMapper.toNotificationDO)
        .collect(Collectors.toList());
  }

  @Override
  public NotificationDO addNotificationToRoute(Long waypointId, NotificationDO notificationDO, User user) {
    Preconditions.checkNull(notificationDO.getNotificationId(), "NotificationId must be null");
    Preconditions.checkNull(notificationDO.getVersion(), "NotificationDO version must be null");

    Preconditions.checkNotNull(notificationDO, "NotificationDO must not be null");
    Preconditions.checkNotNull(notificationDO.getNotificationChannel(), "NotificationDO channel must not be null");
    Preconditions.checkNotNull(notificationDO.getNotifiedAtUtc(), "NotificationDO notifiedAt must not be null");

    Preconditions.checkNotNull(waypointId, "WaypointId must not be null");
    Preconditions.checkArgument(waypointId > 0, "WaypointId must be positive");

    if (notificationDO.getWaypointId() != null) {
      Preconditions.checkArgument(waypointId.equals(notificationDO.getWaypointId()),
          String.format("WaypointId of the path '%s' and the NotificationDO waypointId '%s' are not identical",
              waypointId, notificationDO.getWaypointId()));
    }

    var notificationEntity = NotificationMapper.toNotificationEntity.apply(notificationDO);

    var persistedNotificationEntity = notificationLogic.create(notificationEntity, waypointId, user);

    return NotificationMapper.toNotificationDO.apply(persistedNotificationEntity);
  }
}
