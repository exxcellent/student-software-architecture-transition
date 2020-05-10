package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.RouteCalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.RouteDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.WaypointDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RouteComponent {

  List<RouteDO> findAll(Set<Long> filterByInspectorIds);

  List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds);

  RouteDO findByDateAndInspector(LocalDate date, Long inspector, RouteCalculationMode calculationMode);

  WaypointDO findWaypoint(LocalDate date, Long inspectorId, Long wayPointId);
}
