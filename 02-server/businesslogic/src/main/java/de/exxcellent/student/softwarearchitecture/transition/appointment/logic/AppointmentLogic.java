package de.exxcellent.student.softwarearchitecture.transition.appointment.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.appointment.data.AppointmentRepository;

import de.exxcellent.student.softwarearchitecture.transition.appointment.data.entities.AppointmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
 @Transactional
 public class AppointmentLogic extends CrudLogic<AppointmentEntity> {

  @Autowired
  public AppointmentLogic(AppointmentRepository appointmentRepository, DateTimeUtil dateTimeUtil) {
    super(appointmentRepository, dateTimeUtil);
  }

}
