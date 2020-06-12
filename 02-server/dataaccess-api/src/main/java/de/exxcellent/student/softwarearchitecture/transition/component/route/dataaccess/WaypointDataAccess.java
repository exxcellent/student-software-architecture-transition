package de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess.types.waypoint.WaypointDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface WaypointDataAccess extends CrudDataAccess<WaypointDTO> {

    List<WaypointDTO> findAllByInspectorIdIn(Set<Long> filterByInspectorIds);

    List<WaypointDTO> findAllByDate(LocalDate date);

    List<WaypointDTO> findAllByDateAndInspectorIdIn(LocalDate date, Set<Long> filterByInspectorIds);

    List<WaypointDTO> findAllByDateAndInspectorId(LocalDate date, Long inspectorId);

    WaypointDTO findByDateAndInspectorIdAndId(LocalDate date, Long inspectorId, Long wayPointId);

    WaypointDTO findByDateAndInspectorIdAndOrderIndex(LocalDate date, Long inspectorId, int nextOrderIndex);
}
