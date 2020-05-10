package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.NotificationComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.logic.NotificationLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.mapper.NotificationMapper;
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
  public NotificationDO addNotificationToRoute(LocalDate date, Long inspectorId, NotificationDO notificationDO) {
    return null;
  }
}
