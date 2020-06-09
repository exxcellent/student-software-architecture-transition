package de.exxcellent.student.softwarearchitecture.transition.component.route.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.notification.NotificationDO;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface NotificationComponent extends BusinessComponent {

  List<NotificationDO> findAllNotificationsByDateAndInspector(LocalDate date, Long inspectorId);

  List<NotificationDO> findAllNotificationsByWaypointId(Long waypointId);

  NotificationDO addNotificationToRoute(Long waypointId, NotificationDO notificationDO, User user);
}
