package de.exxcellent.student.softwarearchitecture.transition.navigation.route.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.NotificationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class NotificationLogic extends CrudLogic<NotificationDTO> {

  private final NotificationDataAccess notificationDataAccess;
  private final RouteLogic routeLogic;

  @Autowired
  protected NotificationLogic(NotificationDataAccess notificationDataAccess, RouteLogic routeLogic) {
    super(notificationDataAccess);
    this.notificationDataAccess = notificationDataAccess;
    this.routeLogic = routeLogic;
  }

  public List<NotificationDTO> findAllByDateAndInspectorId(LocalDate date, Long inspectorId) {
    return notificationDataAccess.findAllByDateAndInspectorId(date, inspectorId);
  }

  public List<NotificationDTO> findAllByWaypointId(Long waypointId) {
    return notificationDataAccess.findAllByWaypointId(waypointId);
  }

  public NotificationDTO create(NotificationDTO notificationDTO, Long waypointId, User user) {

    // resolve relationship
    var waypointDTO = routeLogic.findById(waypointId);
    notificationDTO.setWaypoint(waypointDTO);

    return create(notificationDTO, user);
  }
}
