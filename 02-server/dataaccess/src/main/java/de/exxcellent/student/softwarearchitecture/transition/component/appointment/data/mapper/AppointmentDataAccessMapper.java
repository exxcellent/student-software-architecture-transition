package de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types.AppointmentDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AppointmentDataAccessMapper {
  AppointmentEntity toEntity(AppointmentDTO dto);
  AppointmentDTO toDTO(AppointmentEntity entity);
}