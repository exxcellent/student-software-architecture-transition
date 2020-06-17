package de.exxcellent.student.softwarearchitecture.transition.planning.appointments.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.appointments.resources.types.AppointmentTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointments.resources.types.AppointmentsCTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.api.types.AppointmentDO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class AppointmentMapper {

  private final static String DATE_FORMAT = "yyyy-MM-dd";
  private final static String TIME_FORMAT = "HH:mm";

  private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
  private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

  private AppointmentMapper() {}

  public static final Function<List<AppointmentDO>, AppointmentsCTO> toAppointmentsCTO = appointmentDOList -> {
    var appointmentTOs = appointmentDOList.stream()
        .map(AppointmentMapper.toAppointmentTO)
        .collect(Collectors.toList());

    return new AppointmentsCTO(appointmentTOs);
  };

  public static final Function<AppointmentDO, AppointmentTO> toAppointmentTO = appointmentDO -> {
    var appointmentTO = new AppointmentTO();

    appointmentTO.setAppointmentId(appointmentDO.getAppointmentId());
    appointmentTO.setVersion(appointmentDO.getVersion());

    appointmentTO.setDescription(appointmentDO.getDescription());
    appointmentTO.setFinished(appointmentDO.getFinished());
    if (appointmentDO.getDate() != null) {
      appointmentTO.setDate(appointmentDO.getDate().format(DATE_FORMATTER));
    }
    if (appointmentDO.getStartTime() != null) {
      appointmentTO.setStartTime(appointmentDO.getStartTime().format(TIME_FORMATTER));
    }
    appointmentTO.setDurationInSeconds(appointmentDO.getDurationInSeconds());
    appointmentTO.setTravelDurationInSeconds(appointmentDO.getTravelDurationInSeconds());

    appointmentTO.setProcessId(appointmentDO.getProcessId());

    return appointmentTO;
  };

  public static final Function<AppointmentTO, AppointmentDO> toAppointmentDO = appointmentTO -> {
    var appointmentDO = new AppointmentDO();

    appointmentDO.setAppointmentId(appointmentTO.getAppointmentId());
    appointmentDO.setVersion(appointmentTO.getVersion());

    appointmentDO.setDescription(appointmentTO.getDescription());
    appointmentDO.setFinished(appointmentTO.getFinished());
    appointmentDO.setDate(LocalDate.from(DATE_FORMATTER.parse(appointmentTO.getDate())));
    appointmentDO.setStartTime(LocalTime.from(TIME_FORMATTER.parse(appointmentTO.getStartTime())));
    appointmentDO.setDurationInSeconds(appointmentTO.getDurationInSeconds());
    appointmentDO.setTravelDurationInSeconds(appointmentTO.getTravelDurationInSeconds());

    appointmentDO.setProcessId(appointmentTO.getProcessId());

    return appointmentDO;
  };
}
