package de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types.AppointmentDTO;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class AppointmentDataAccessMapper {

  private AppointmentDataAccessMapper() {}

  public static final Function<AppointmentEntity, AppointmentDTO> toAppointmentDTO = entity -> {
    var appointmentDTO = new AppointmentDTO();

    appointmentDTO.setId(entity.getId());
    appointmentDTO.setVersion(entity.getVersion());

    appointmentDTO.setDescription(entity.getDescription());
    appointmentDTO.setFinished(entity.getFinished());

    appointmentDTO.setDate(entity.getDate());
    appointmentDTO.setStartTime(entity.getStartTime());

    if (entity.getAppointmentDuration() != null) {
      appointmentDTO.setAppointmentDuration(entity.getAppointmentDuration());
    }
    if (entity.getTravelDuration() != null) {
      appointmentDTO.setTravelDuration(entity.getTravelDuration());
    }

    appointmentDTO.setProcessId(entity.getProcessId());

    return appointmentDTO;
  };

  public static final Function<AppointmentDTO, AppointmentEntity > toAppointmentEntity = appointmentDTO -> {
    var appointmentEntity = new AppointmentEntity();

    appointmentEntity.setId(appointmentDTO.getId());
    appointmentEntity.setVersion(appointmentDTO.getVersion());

    appointmentEntity.setDescription(appointmentDTO.getDescription());
    appointmentEntity.setFinished(appointmentDTO.getFinished());

    appointmentEntity.setDate(appointmentDTO.getDate());
    appointmentEntity.setStartTime(appointmentDTO.getStartTime());
    appointmentEntity.setAppointmentDuration(appointmentDTO.getAppointmentDuration());
    appointmentEntity.setTravelDuration(appointmentDTO.getTravelDuration());

    appointmentEntity.setProcessId(appointmentDTO.getProcessId());

    return appointmentEntity;
  };
}
