package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.mapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentEntity;
import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class AppointmentMapper {

  private AppointmentMapper() {}

  public static final Function<AppointmentEntity, AppointmentDO> toAppointmentDO = entity -> {
    var appointmentDO = new AppointmentDO();

    // TODO: set parameters

    return appointmentDO;
  };
}
