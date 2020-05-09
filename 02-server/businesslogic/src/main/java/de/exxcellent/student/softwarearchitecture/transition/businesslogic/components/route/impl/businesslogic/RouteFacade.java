package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.RouteComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.RouteDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.WaypointDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.logic.RouteLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.mapper.RouteMapper;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.WaypointEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.RouteCalculationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RouteFacade implements RouteComponent {

  private final RouteLogic routeLogic;
  private final RouteCalculationComponent routeCalculationComponent;

  @Autowired
  public RouteFacade(RouteLogic routeLogic, RouteCalculationComponent routeCalculationComponent) {
    this.routeLogic = routeLogic;
    this.routeCalculationComponent = routeCalculationComponent;
  }


  @Override
  public List<RouteDO> findAll(Set<Long> filterByInspectorIds) {
    var waypoints = routeLogic.findAllByInspectors(filterByInspectorIds);

    // Map<LocalDate, Map<Long, List<WaypointDO>
    return calculateRoutes(waypoints);
  }

  @Override
  public List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds) {
    Preconditions.checkNotNull(date, "Date must not be null");

    var waypoints = routeLogic.findAllByDate(date, filterByInspectorIds);

    // Map<LocalDate, Map<Long, List<WaypointDO>
    return calculateRoutes(waypoints);
  }

  @Override
  public RouteDO findAllByDateAndInspector(LocalDate date, Long inspector) {
    Preconditions.checkNotNull(date, "Date must not be null");
    Preconditions.checkNotNull(inspector, "InspectorId must not be null");
    Preconditions.checkArgument(inspector > 0, "InspectorId must be positive");

    var waypoints = routeLogic.findAllByDateAndInspector(date, inspector);

    return calculateRoutes(waypoints).get(0);
  }

  private List<RouteDO> calculateRoutes(List<WaypointEntity> waypoints) {
    // map to routes
    // groupBy date and inspectorId
    var dailyInspectorWaypoints = waypoints.stream()
        .map(RouteMapper.toWaypointDO)
        .collect(Collectors.groupingBy(WaypointDO::getDate, Collectors.groupingBy(WaypointDO::getInspectorId)));

    return dailyInspectorWaypoints.entrySet().stream()
        // (LocalDate, Map<Long, List<WaypointDO>)
        .map(entry -> {
          // iterate on List<WaypointDO>
          return entry.getValue().values().stream()
              // map to RouteDO
              .map(waypointDOS -> {
                var route = new RouteDO();
                route.setDate(entry.getKey());
                route.setWaypoints(waypointDOS);
                return route;
              })
              .peek(route -> {
                // calculate travel duration
                var locations = RouteMapper.toLocationRequestDOList.apply(route.getWaypoints());
                var calculatedRoute = routeCalculationComponent.calculateRoute(locations);
                // update RouteDO by reference
                route.setTotalDurationInSeconds(Duration.of(calculatedRoute.getTravelDurationInSeconds(), ChronoUnit.SECONDS));
                route.setTimeRemainingInSeconds(route.getTotalDurationInSeconds());
              })
              .collect(Collectors.toList());

        })
        // List<List<RouteDO> -> List<RouteDO>
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }


}
