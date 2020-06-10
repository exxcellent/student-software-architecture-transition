package de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.entities.InspectorEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.types.InspectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InspectorDataAccessMapper {
  InspectorEntity toEntity(InspectorDTO dto);
  InspectorDTO toDTO(InspectorEntity entity);
}