package de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.mapper;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentPriority;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentType;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentsCTO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.Priority;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.List;
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

    appointmentTO.setTitle(appointmentDO.getTitle());
    appointmentTO.setDescription(appointmentDO.getDescription());
    appointmentTO.setAppointmentPriority(AppointmentMapper.toAppointmentPriority
        .apply(appointmentDO.getAppointmentPriority()));
    appointmentTO.setAppointmentType(AppointmentMapper.toAppointmentType
        .apply(appointmentDO.getAppointmentType()));
    appointmentTO.setFinished(appointmentDO.getFinished());
    appointmentTO.setDate(appointmentDO.getDate().format(DATE_FORMATTER));
    appointmentTO.setStartTime(appointmentDO.getStartTime().format(TIME_FORMATTER));
    appointmentTO.setEndTime(appointmentDO.getEndTime().format(TIME_FORMATTER));
    appointmentTO.setTravelDurationInSeconds(appointmentDO.getTravelDurationInSeconds());

    appointmentTO.setInspectorId(appointmentDO.getInspectorId());
    appointmentTO.setLocationId(appointmentDO.getLocationId());
    appointmentTO.setContactId(appointmentDO.getContactId());

    return appointmentTO;
  };

  public static final Function<AppointmentTO, AppointmentDO> toAppointmentDO = appointmentTO -> {
    var appointmentDO = new AppointmentDO();

    appointmentDO.setAppointmentId(appointmentTO.getAppointmentId());
    appointmentDO.setVersion(appointmentTO.getVersion());

    appointmentDO.setTitle(appointmentTO.getTitle());
    appointmentDO.setDescription(appointmentTO.getDescription());
    appointmentDO.setAppointmentPriority(AppointmentMapper.fromAppointmentPriority
      .apply(appointmentTO.getAppointmentPriority()));
    appointmentDO.setAppointmentType(AppointmentMapper.fromAppointmentType
      .apply(appointmentTO.getAppointmentType()));
    appointmentDO.setFinished(appointmentTO.getFinished());
    appointmentDO.setDate(LocalDate.from(DATE_FORMATTER.parse(appointmentTO.getDate())));
    appointmentDO.setStartTime(LocalTime.from(TIME_FORMATTER.parse(appointmentTO.getStartTime())));
    appointmentDO.setEndTime(LocalTime.from(TIME_FORMATTER.parse(appointmentTO.getEndTime())));
    appointmentDO.setTravelDurationInSeconds(appointmentTO.getTravelDurationInSeconds());

    appointmentDO.setInspectorId(appointmentTO.getInspectorId());
    appointmentDO.setLocationId(appointmentTO.getLocationId());
    appointmentDO.setContactId(appointmentTO.getContactId());

    return appointmentDO;
  };


  private static final Function<AppointmentType, Type> fromAppointmentType = appointmentType -> {
    switch (appointmentType) {
      case INITIAL_INSTRUCTION: return Type.INITIAL_INSTRUCTION;
      case WATER: return Type.WATER;
      case ROAD_CONSTRUCTION: return Type.ROAD_CONSTRUCTION;
      case SPECIAL_USE: return Type.SPECIAL_USE;
      case SEWER_CONSTRUCTION: return Type.SEWER_CONSTRUCTION;
      case TELECOMMUNICATIONS: return Type.TELECOMMUNICATIONS;
      case NORMAL:
      default:
        return Type.NORMAL;
    }
  };

  private static final Function<Type, AppointmentType> toAppointmentType = type -> {
    switch (type) {
      case INITIAL_INSTRUCTION: return AppointmentType.INITIAL_INSTRUCTION;
      case WATER: return AppointmentType.WATER;
      case ROAD_CONSTRUCTION: return AppointmentType.ROAD_CONSTRUCTION;
      case SPECIAL_USE: return AppointmentType.SPECIAL_USE;
      case SEWER_CONSTRUCTION: return AppointmentType.SEWER_CONSTRUCTION;
      case TELECOMMUNICATIONS: return AppointmentType.TELECOMMUNICATIONS;
      case NORMAL:
      default:
        return AppointmentType.NORMAL;
    }
  };

  private static final Function<AppointmentPriority, Priority> fromAppointmentPriority = appointmentPriority -> {
    switch (appointmentPriority) {
      case DANGEROUS: return Priority.DANGEROUS;
      case URGENT: return Priority.URGENT;
      case NORMAL:
      default:
        return Priority.NORMAL;
    }
  };

  private static final Function<Priority, AppointmentPriority> toAppointmentPriority = priority -> {
    switch (priority) {
      case DANGEROUS: return AppointmentPriority.DANGEROUS;
      case URGENT: return AppointmentPriority.URGENT;
      case NORMAL:
      default:
        return AppointmentPriority.NORMAL;
    }
  };
}
