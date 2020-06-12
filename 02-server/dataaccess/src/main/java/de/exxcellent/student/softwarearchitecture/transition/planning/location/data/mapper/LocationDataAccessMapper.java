package de.exxcellent.student.softwarearchitecture.transition.planning.location.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.location.data.entities.LocationEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.dataaccess.types.LocationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDataAccessMapper {
  LocationEntity toEntity(LocationDTO dto);
  LocationDTO toDTO(LocationEntity entity);
}