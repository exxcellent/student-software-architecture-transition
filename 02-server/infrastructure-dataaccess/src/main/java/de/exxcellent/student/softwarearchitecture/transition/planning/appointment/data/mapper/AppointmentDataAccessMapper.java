package de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.dataaccess.types.AppointmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentDataAccessMapper {
  AppointmentEntity toEntity(AppointmentDTO dto);
  AppointmentDTO toDTO(AppointmentEntity entity);
}