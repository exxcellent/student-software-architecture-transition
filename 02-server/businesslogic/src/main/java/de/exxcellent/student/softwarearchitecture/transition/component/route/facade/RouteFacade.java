package de.exxcellent.student.softwarearchitecture.transition.component.route.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.RouteComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.*;
import de.exxcellent.student.softwarearchitecture.transition.component.route.logic.RouteLogic;
import de.exxcellent.student.softwarearchitecture.transition.component.route.mapper.RouteMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.route.data.entities.waypoint.WaypointEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.api.RouteCalculationComponent;
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
    return calculateRoutes(waypoints, RouteCalculationMode.RANDOM);
  }

  @Override
  public List<RouteDO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds) {
    Preconditions.checkNotNull(date, "Date must not be null");

    var waypoints = routeLogic.findAllByDate(date, filterByInspectorIds);

    // Map<LocalDate, Map<Long, List<WaypointDO>
    return calculateRoutes(waypoints, RouteCalculationMode.RANDOM);
  }

  @Override
  public RouteDO findByDateAndInspector(LocalDate date, Long inspector, RouteCalculationMode calculationMode) {
    Preconditions.checkNotNull(date, "Date must not be null");
    Preconditions.checkNotNull(inspector, "InspectorId must not be null");
    Preconditions.checkArgument(inspector > 0, "InspectorId must be positive");

    var waypoints = routeLogic.findAllByDateAndInspector(date, inspector);

    if (waypoints == null || waypoints.isEmpty()) {
      return null;
    }

    return calculateRoutes(waypoints, calculationMode).get(0);
  }

  @Override
  public WaypointDO findWaypoint(LocalDate date, Long inspectorId, Long wayPointId) {
    Preconditions.checkNotNull(date, "Date must not be null");
    Preconditions.checkNotNull(inspectorId, "InspectorId must not be null");
    Preconditions.checkArgument(inspectorId > 0, "InspectorId must be positive");
    Preconditions.checkNotNull(wayPointId, "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId > 0, "WaypointId must be positive");

    var waypoint = routeLogic.findByDateAndInspectorAndWaypointId(date, inspectorId, wayPointId);

    return RouteMapper.toWaypointDO.apply(waypoint);
  }

  @Override
  public WaypointDO findWaypoint(Long wayPointId) {
    Preconditions.checkNotNull(wayPointId, "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId > 0, "WaypointId must be positive");

    var waypoint = routeLogic.findById(wayPointId);

    return RouteMapper.toWaypointDO.apply(waypoint);
  }


  @Override
  public WaypointDO updateWaypoint(Long wayPointId, WaypointDO waypointDO, User user) {
    Preconditions.checkNotNull(wayPointId, "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId > 0, "WaypointId must be positive");

    Preconditions.checkNotNull(waypointDO, "Waypoint must not be null");
    Preconditions.checkNotNull(waypointDO.getWaypointId(), "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId.equals(waypointDO.getWaypointId()),
        String.format("WaypointId of the path '%s' and the waypointId of the payload '%s' must be equals",
            wayPointId, waypointDO.getWaypointId()));
    Preconditions.checkNotNull(waypointDO.getVersion(), "Waypoint version must not be null");
    Preconditions.checkArgument(waypointDO.getVersion() >= 0, "Waypoint version must be positive");

    var waypointEntity = RouteMapper.toWaypointEntity.apply(waypointDO);
    var updatedWaypoint = routeLogic.update(waypointEntity, user);

    return RouteMapper.toWaypointDO.apply(updatedWaypoint);
  }

  @Override
  public UpdatedWaypointDO finishWaypoint(Long wayPointId, WaypointDO waypointDO, User user) {
    Preconditions.checkNotNull(wayPointId, "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId > 0, "WaypointId must be positive");

    Preconditions.checkNotNull(waypointDO, "Waypoint must not be null");
    Preconditions.checkNotNull(waypointDO.getWaypointId(), "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId.equals(waypointDO.getWaypointId()),
        String.format("WaypointId of the path '%s' and the waypointId of the payload '%s' must be equals",
            wayPointId, waypointDO.getWaypointId()));
    Preconditions.checkNotNull(waypointDO.getVersion(), "Waypoint version must not be null");
    Preconditions.checkArgument(waypointDO.getVersion() >= 0, "Waypoint version must be positive");

    Preconditions.checkArgument(waypointDO.getStatus() == WaypointStatus.FINISHED, "Waypoint must be FINISHED");

    var waypointEntity = RouteMapper.toWaypointEntity.apply(waypointDO);
    var waypointPair = routeLogic.finishWaypoint(waypointEntity, user);

    var updatedWaypoints = new UpdatedWaypointDO();
    updatedWaypoints.setUpdated(RouteMapper.toWaypointDO.apply(waypointPair.left));

    if (waypointPair.right != null) {
      updatedWaypoints.setNext(RouteMapper.toWaypointDO.apply(waypointPair.right));
    }

    return updatedWaypoints;
  }

  @Override
  public UpdatedWaypointDO cancelWaypoint(Long wayPointId, WaypointDO waypointDO, User user) {
    Preconditions.checkNotNull(wayPointId, "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId > 0, "WaypointId must be positive");

    Preconditions.checkNotNull(waypointDO, "Waypoint must not be null");
    Preconditions.checkNotNull(waypointDO.getWaypointId(), "WaypointId must not be null");
    Preconditions.checkArgument(wayPointId.equals(waypointDO.getWaypointId()),
        String.format("WaypointId of the path '%s' and the waypointId of the payload '%s' must be equals",
            wayPointId, waypointDO.getWaypointId()));
    Preconditions.checkNotNull(waypointDO.getVersion(), "Waypoint version must not be null");
    Preconditions.checkArgument(waypointDO.getVersion() >= 0, "Waypoint version must be positive");

    Preconditions.checkArgument(waypointDO.getStatus() == WaypointStatus.CANCELED, "Waypoint must be CANCELED");

    var waypointEntity = RouteMapper.toWaypointEntity.apply(waypointDO);
    var waypointPair = routeLogic.cancelWaypoint(waypointEntity, user);

    var updatedWaypoints = new UpdatedWaypointDO();
    updatedWaypoints.setUpdated(RouteMapper.toWaypointDO.apply(waypointPair.left));

    if (waypointPair.right != null) {
      updatedWaypoints.setNext(RouteMapper.toWaypointDO.apply(waypointPair.right));
    }

    return updatedWaypoints;
  }

  private List<RouteDO> calculateRoutes(List<WaypointEntity> waypoints, RouteCalculationMode routeCalculationMode) {
    var mode = RouteMapper.toCalculationMode.apply(routeCalculationMode);

    // map to routes
    // groupBy date and inspectorId
    return waypoints.stream()
        .map(RouteMapper.toWaypointDO)
        .collect(Collectors.collectingAndThen(
            Collectors.groupingBy(WaypointDO::getDate, Collectors.groupingBy(WaypointDO::getInspectorId)),
            // Map<LocalDate, Map<Long, List<WaypointDO>>
            dailyInspectorWaypointList -> {
              return dailyInspectorWaypointList.entrySet().stream()
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
                        var calculatedRoute = routeCalculationComponent.calculateRoute(locations, mode);
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
        ));
  }


}
