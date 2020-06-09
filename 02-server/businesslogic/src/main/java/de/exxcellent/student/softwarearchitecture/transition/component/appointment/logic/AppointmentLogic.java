package de.exxcellent.student.softwarearchitecture.transition.component.appointment.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.AppointmentDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types.AppointmentDTO;
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
 public class AppointmentLogic extends CrudLogic<AppointmentDTO> {

  @Autowired
  public AppointmentLogic(AppointmentDataAccess appointmentDataAccess) {
    super(appointmentDataAccess);
  }

}
