package de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.mapper;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentsCTO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;

import java.util.function.Function;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class AppointmentMapper {

  private AppointmentMapper() {}

  public static final Function<List<AppointmentDO>, AppointmentsCTO> toAppointmentsCTO = appointmentDOList -> {
    var appointmentTOs = appointmentDOList.stream()
        .map(AppointmentMapper.toAppointmentTO)
        .collect(Collectors.toList());

    var appointmentsCTO = new AppointmentsCTO(appointmentTOs);

    return appointmentsCTO;
  };

  public static final Function<AppointmentDO, AppointmentTO> toAppointmentTO = appointmentDO -> {
    var appointmentTO = new AppointmentTO();

    // TODO: set parameters

    return appointmentTO;
  };

  public static final Function<AppointmentTO, AppointmentDO> toAppointmentDO = appointmentTO -> {
      var appointmentDO = new AppointmentDO();

      // TODO: set parameters

      return appointmentDO;
    };
}
