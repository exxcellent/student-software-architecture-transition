package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.NotificationRepository;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.notification.NotificationEntity;
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

  @Autowired
  protected NotificationLogic(NotificationRepository repository, DateTimeUtil dateTimeUtil) {
    super(repository, dateTimeUtil);
    this.notificationRepository = repository;
  }

  public List<NotificationEntity> findAllByDateAndInspectorId(LocalDate date, Long inspectorId) {
    return notificationRepository.findAllByDateAndInspectorId(date, inspectorId);
  }
}
