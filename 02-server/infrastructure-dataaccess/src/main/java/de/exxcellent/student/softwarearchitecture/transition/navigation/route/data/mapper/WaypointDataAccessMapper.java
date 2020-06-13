package de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.entities.waypoint.WaypointEntity;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.WaypointDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaypointDataAccessMapper {
  WaypointEntity toEntity(WaypointDTO dto);
  WaypointDTO toDTO(WaypointEntity entity);
}