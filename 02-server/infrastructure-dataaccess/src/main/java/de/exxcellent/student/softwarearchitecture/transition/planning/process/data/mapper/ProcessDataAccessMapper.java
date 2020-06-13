package de.exxcellent.student.softwarearchitecture.transition.planning.process.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.entities.ProcessEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.dataaccess.types.ProcessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessDataAccessMapper {
  ProcessEntity toEntity(ProcessDTO dto);
  ProcessDTO toDTO(ProcessEntity entity);
}