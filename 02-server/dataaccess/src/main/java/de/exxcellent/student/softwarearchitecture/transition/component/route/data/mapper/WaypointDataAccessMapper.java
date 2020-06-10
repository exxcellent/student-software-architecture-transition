package de.exxcellent.student.softwarearchitecture.transition.component.route.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.route.data.entities.waypoint.WaypointEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess.types.waypoint.WaypointDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaypointDataAccessMapper {
  WaypointEntity toEntity(WaypointDTO dto);
  WaypointDTO toDTO(WaypointEntity entity);
}