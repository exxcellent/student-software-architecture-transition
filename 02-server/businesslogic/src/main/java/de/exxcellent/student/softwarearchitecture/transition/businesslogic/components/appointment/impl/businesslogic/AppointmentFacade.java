package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.AppointmentComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.logic.AppointmentLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.mapper.AppointmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class AppointmentFacade implements AppointmentComponent {

  private final AppointmentLogic appointmentLogic;

  @Autowired
  public AppointmentFacade(AppointmentLogic appointmentLogic) {
    this.appointmentLogic = appointmentLogic;
  }

    @Override
    public List<AppointmentDO> findAll() {
      return null;
    }
  
    @Override
    public AppointmentDO findById(Long appointmentId) {
      return null;
    }
  
    @Override
    public AppointmentDO create(AppointmentDO newAppointmentDO, User user) {
      return null;
    }
  
    @Override
    public AppointmentDO update(AppointmentDO appointmentDO, User user) {
      return null;
    }
  
    @Override
    public void delete(AppointmentDO appointmentDO) {
  
    }
}
