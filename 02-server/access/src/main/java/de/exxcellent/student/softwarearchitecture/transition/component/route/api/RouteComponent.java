package de.exxcellent.student.softwarearchitecture.transition.component.route.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.RouteCalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.RouteDO;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.UpdatedWaypointDO;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.WaypointDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RouteComponent {

  List<RouteDO> findAll(Set<Long> filterByInspectorIds);

  List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds);

  RouteDO findByDateAndInspector(LocalDate date, Long inspector, RouteCalculationMode calculationMode);

  WaypointDO findWaypoint(LocalDate date, Long inspectorId, Long wayPointId);
  WaypointDO findWaypoint(Long wayPointId);

  WaypointDO updateWaypoint(Long wayPointId, WaypointDO waypointDO, User user);
  UpdatedWaypointDO finishWaypoint(Long wayPointId, WaypointDO waypointDO, User user);
  UpdatedWaypointDO cancelWaypoint(Long wayPointId, WaypointDO waypointDO, User user);
}
