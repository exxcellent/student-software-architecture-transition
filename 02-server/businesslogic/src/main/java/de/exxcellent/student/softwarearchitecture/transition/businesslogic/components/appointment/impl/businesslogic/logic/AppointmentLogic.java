package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.AppointmentRepository;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class AppointmentLogic extends CrudLogic<AppointmentEntity> {

  @Autowired
  public AppointmentLogic(AppointmentRepository appointmentRepository, DateTimeUtil dateTimeUtil) {
    super(appointmentRepository, dateTimeUtil);
  }

}
