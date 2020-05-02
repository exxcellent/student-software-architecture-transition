package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.mapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.Priority;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.Type;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentPriority;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentType;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
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

    appointmentDO.setAppointmentId(entity.getId());
    appointmentDO.setVersion(entity.getVersion());

    appointmentDO.setTitle(entity.getTitle());
    appointmentDO.setDescription(entity.getDescription());
    appointmentDO.setAppointmentPriority(AppointmentMapper.toPriority
        .apply(entity.getAppointmentPriority()));
    appointmentDO.setAppointmentType(AppointmentMapper.toType
        .apply(entity.getAppointmentType()));
    appointmentDO.setFinished(entity.getFinished());

    appointmentDO.setDate(entity.getDate());
    appointmentDO.setStartTime(entity.getStartTime());
    appointmentDO.setEndTime(entity.getEndTime());
    appointmentDO.setTravelDurationInSeconds(entity.getTravelDuration().toSeconds());

    appointmentDO.setInspectorId(entity.getInspectorId());
    appointmentDO.setLocationId(entity.getLocationId());
    appointmentDO.setContactId(entity.getContactId());

    return appointmentDO;
  };

  public static final Function<AppointmentDO, AppointmentEntity> toAppointmentEntity = appointmentDO -> {
    var appointmentEntity = new AppointmentEntity();

    appointmentEntity.setId(appointmentDO.getAppointmentId());
    appointmentEntity.setVersion(appointmentDO.getVersion());

    appointmentEntity.setTitle(appointmentDO.getTitle());
    appointmentEntity.setDescription(appointmentDO.getDescription());
    appointmentEntity.setAppointmentPriority(AppointmentMapper.fromPriority
        .apply(appointmentDO.getAppointmentPriority()));
    appointmentEntity.setAppointmentType(AppointmentMapper.fromType
        .apply(appointmentDO.getAppointmentType()));
    appointmentEntity.setFinished(appointmentDO.getFinished());

    appointmentEntity.setDate(appointmentDO.getDate());
    appointmentEntity.setStartTime(appointmentDO.getStartTime());
    appointmentEntity.setEndTime(appointmentDO.getEndTime());
    appointmentEntity.setTravelDuration(
        Duration.of(appointmentDO.getTravelDurationInSeconds(), ChronoUnit.SECONDS));

    appointmentEntity.setInspectorId(appointmentDO.getInspectorId());
    appointmentEntity.setLocationId(appointmentDO.getLocationId());
    appointmentEntity.setContactId(appointmentDO.getContactId());

    return appointmentEntity;
  };

  private static final Function<AppointmentType, Type> toType = appointmentType -> {
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

  private static final Function<Type, AppointmentType> fromType = type -> {
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

  private static final Function<AppointmentPriority, Priority> toPriority = appointmentPriority -> {
    switch (appointmentPriority) {
      case DANGEROUS: return Priority.DANGEROUS;
      case URGENT: return Priority.URGENT;
      case NORMAL:
      default:
        return Priority.NORMAL;
    }
  };

  private static final Function<Priority, AppointmentPriority> fromPriority = priority -> {
    switch (priority) {
      case DANGEROUS: return AppointmentPriority.DANGEROUS;
      case URGENT: return AppointmentPriority.URGENT;
      case NORMAL:
      default:
        return AppointmentPriority.NORMAL;
    }
  };
}
