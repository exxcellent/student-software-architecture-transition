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

    appointmentDO.setAppointmentId(entity.getId());
    appointmentDO.setVersion(entity.getVersion());

    appointmentDO.setDescription(entity.getDescription());
    appointmentDO.setFinished(entity.getFinished());

    appointmentDO.setDate(entity.getDate());
    appointmentDO.setStartTime(entity.getStartTime());

    if (entity.getAppointmentDuration() != null) {
      appointmentDO.setDurationInSeconds(entity.getAppointmentDuration());
    }
    if (entity.getTravelDuration() != null) {
      appointmentDO.setTravelDurationInSeconds(entity.getTravelDuration());
    }

    appointmentDO.setProcessId(entity.getProcessId());

    return appointmentDO;
  };

  public static final Function<AppointmentDO, AppointmentEntity> toAppointmentEntity = appointmentDO -> {
    var appointmentEntity = new AppointmentEntity();

    appointmentEntity.setId(appointmentDO.getAppointmentId());
    appointmentEntity.setVersion(appointmentDO.getVersion());

    appointmentEntity.setDescription(appointmentDO.getDescription());
    appointmentEntity.setFinished(appointmentDO.getFinished());

    appointmentEntity.setDate(appointmentDO.getDate());
    appointmentEntity.setStartTime(appointmentDO.getStartTime());
    appointmentEntity.setAppointmentDuration(appointmentDO.getDurationInSeconds());
    appointmentEntity.setTravelDuration(appointmentDO.getTravelDurationInSeconds());

    appointmentEntity.setProcessId(appointmentDO.getProcessId());

    return appointmentEntity;
  };
}
