package de.exxcellent.student.softwarearchitecture.transition.planning.appointment.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.api.types.AppointmentDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface AppointmentComponent {

  List<AppointmentDO> findAll();

  AppointmentDO findById(Long appointmentId);

  AppointmentDO create(AppointmentDO newappointmentDO, User user);

  AppointmentDO update(AppointmentDO appointmentDO, User user);

  void delete(Long id);
}
