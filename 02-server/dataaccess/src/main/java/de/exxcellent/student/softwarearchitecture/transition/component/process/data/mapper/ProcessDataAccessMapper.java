package de.exxcellent.student.softwarearchitecture.transition.component.process.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.process.data.entities.ProcessEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.process.dataaccess.types.ProcessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessDataAccessMapper {
  ProcessEntity toEntity(ProcessDTO dto);
  ProcessDTO toDTO(ProcessEntity entity);
}