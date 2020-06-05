package de.exxcellent.student.softwarearchitecture.transition.route.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.route.data.NotificationRepository;
import de.exxcellent.student.softwarearchitecture.transition.route.data.entities.notification.NotificationEntity;
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
public class NotificationLogic extends CrudLogic<NotificationEntity> {

  private final NotificationRepository notificationRepository;
  private final RouteLogic routeLogic;

  @Autowired
  protected NotificationLogic(NotificationRepository repository, DateTimeUtil dateTimeUtil, RouteLogic routeLogic) {
    super(repository, dateTimeUtil);
    this.notificationRepository = repository;
    this.routeLogic = routeLogic;
  }

  public List<NotificationEntity> findAllByDateAndInspectorId(LocalDate date, Long inspectorId) {
    return notificationRepository.findAllByDateAndInspectorId(date, inspectorId);
  }

  public List<NotificationEntity> findAllByWaypointId(Long waypointId) {
    return notificationRepository.findAllByWaypointId(waypointId);
  }

  public NotificationEntity create(NotificationEntity notificationEntity, Long waypointId, User user) {

    // resolve relationship
    var waypointEntity = routeLogic.findById(waypointId);
    notificationEntity.setWaypoint(waypointEntity);

    return create(notificationEntity, user);
  }
}
