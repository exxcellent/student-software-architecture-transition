package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.route.RouteCalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.route.RouteDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.route.WaypointDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RouteComponent {

  List<RouteDO> findAll(Set<Long> filterByInspectorIds);

  List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds);

  RouteDO findByDateAndInspector(LocalDate date, Long inspector, RouteCalculationMode calculationMode);

  WaypointDO findWaypoint(LocalDate date, Long inspectorId, Long wayPointId);
}
