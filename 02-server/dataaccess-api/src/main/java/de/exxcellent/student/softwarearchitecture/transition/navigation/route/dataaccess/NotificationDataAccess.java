package de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.notification.NotificationDTO;

import java.time.LocalDate;
import java.util.List;

public interface NotificationDataAccess extends CrudDataAccess<NotificationDTO> {

    List<NotificationDTO> findAllByDateAndInspectorId(LocalDate date, Long inspectorId);

    List<NotificationDTO> findAllByWaypointId(Long waypointId);
}
