package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.notification.NotificationDO;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface NotificationComponent extends BusinessComponent {

  List<NotificationDO> findAllNotificationsByDateAndInspector(LocalDate date, Long inspectorId);

  NotificationDO addNotificationToRoute(LocalDate date, Long inspectorId, NotificationDO notificationDO);
}
