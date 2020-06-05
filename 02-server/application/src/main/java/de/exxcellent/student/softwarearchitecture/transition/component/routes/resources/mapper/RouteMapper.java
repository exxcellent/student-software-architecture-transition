package de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.*;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class RouteMapper {

  private final static String DATE_FORMAT = "yyyy-MM-dd";
  private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

  private RouteMapper() {}

  public static Function<List<RouteDO>, RoutesCTO> toRoutesCTO = routes -> {
    var routesCTO = new RoutesCTO();

    routesCTO.setRoutes(routes.stream().map(RouteMapper.toRouteCTO).collect(Collectors.toList()));

    return routesCTO;
  };

  public static Function<RouteDO, RouteCTO> toRouteCTO = route -> {
    var routeCTO = new RouteCTO();

    routeCTO.setDate(route.getDate().format(DATE_FORMATTER));
    routeCTO.setTotalDurationInSeconds(route.getTotalDurationInSeconds().toSeconds());
    routeCTO.setTimeRemainingInSeconds(route.getTimeRemainingInSeconds().toSeconds());
    routeCTO.setWaypoints(route.getWaypoints().stream()
        .map(RouteMapper.toRouteWaypointTO)
        .collect(Collectors.toList()));

    return routeCTO;
  };


  public static Function<RouteDO, RouteWaypointsCTO> toRouteWaypointsCTO = route -> {
    var routeWaypointsCTO = new RouteWaypointsCTO();

    routeWaypointsCTO.setWaypoints(route.getWaypoints().stream()
        .map(RouteMapper.toRouteWaypointTO)
        .collect(Collectors.toList()));

    return routeWaypointsCTO;
  };

  public static Function<UpdatedWaypointDO, WaypointUpdateCTO> toWaypointUpdateCTO = waypoint -> {
    var waypointUpdateCTO = new WaypointUpdateCTO();

    waypointUpdateCTO.setUpdatedWaypoint(RouteMapper.toRouteWaypointTO.apply(waypoint.getUpdated()));
    waypointUpdateCTO.setNextWaypoint(RouteMapper.toRouteWaypointTO.apply(waypoint.getNext()));

    return waypointUpdateCTO;
  };

  public static Function<WaypointDO, RouteWaypointTO> toRouteWaypointTO = waypoint -> {
    var routeWaypointTO = new RouteWaypointTO();

    routeWaypointTO.setWaypointId(waypoint.getWaypointId());
    routeWaypointTO.setVersion(waypoint.getVersion());
    routeWaypointTO.setAppointmentId(waypoint.getAppointmentId());
    routeWaypointTO.setInspectorId(waypoint.getInspectorId());

    routeWaypointTO.setCategory(RouteMapper.toRouteWaypointCategory.apply(waypoint.getCategory()));
    routeWaypointTO.setStatus(RouteMapper.toRouteWaypointStatus.apply(waypoint.getStatus()));
    routeWaypointTO.setDate(waypoint.getDate().format(DATE_FORMATTER));
    routeWaypointTO.setOrderIndex(waypoint.getOrderIndex());

    routeWaypointTO.setContact(RouteMapper.toRouteWaypointContactTO.apply(waypoint));
    routeWaypointTO.setLocation(RouteMapper.toRouteWaypointLocationTO.apply(waypoint));

    return routeWaypointTO;
  };

  public static Function<RouteWaypointTO, WaypointDO> toWaypointDO = waypoint -> {
    var waypointDO = new WaypointDO();

    waypointDO.setWaypointId(waypoint.getWaypointId());
    waypointDO.setVersion(waypoint.getVersion());
    waypointDO.setAppointmentId(waypoint.getAppointmentId());
    waypointDO.setInspectorId(waypoint.getInspectorId());

    waypointDO.setCategory(RouteMapper.fromRouteWaypointCategory.apply(waypoint.getCategory()));
    waypointDO.setStatus(RouteMapper.fromRouteWaypointStatus.apply(waypoint.getStatus()));
    waypointDO.setDate(LocalDate.parse(waypoint.getDate(), DATE_FORMATTER));
    waypointDO.setOrderIndex(waypoint.getOrderIndex());

    waypointDO.setAddress(waypoint.getLocation().getAddress());
    waypointDO.setLatitude(waypoint.getLocation().getLatitude());
    waypointDO.setLongitude(waypoint.getLocation().getLongitude());

    waypointDO.setContactName(waypoint.getContact().getName());
    waypointDO.setEmail(waypoint.getContact().getEmail());
    waypointDO.setPhoneNumber(waypoint.getContact().getPhoneNumber());

    return waypointDO;
  };

  public static Function<WaypointDO, RouteWaypointContactTO> toRouteWaypointContactTO = waypoint -> {
    var routeWaypointContactTO = new RouteWaypointContactTO();

    routeWaypointContactTO.setName(waypoint.getContactName());
    routeWaypointContactTO.setEmail(waypoint.getEmail());
    routeWaypointContactTO.setPhoneNumber(waypoint.getPhoneNumber());

    return routeWaypointContactTO;
  };

  public static Function<WaypointDO, RouteWaypointLocationTO> toRouteWaypointLocationTO = waypoint -> {
    var routeWaypointLocationTO = new RouteWaypointLocationTO();

    routeWaypointLocationTO.setAddress(waypoint.getAddress());
    routeWaypointLocationTO.setLatitude(waypoint.getLatitude());
    routeWaypointLocationTO.setLongitude(waypoint.getLongitude());

    return routeWaypointLocationTO;
  };

  public static Function<String, LocalDate> toLocalDate = dateString -> LocalDate.parse(dateString, DATE_FORMATTER);

  public static Function<List<String>, Set<Long>> toInspectorIdList = inspectors -> {
    if (inspectors == null || inspectors.isEmpty()) {
      return new HashSet<>();
    }

    return inspectors.stream()
        .map(Long::valueOf)
        .collect(Collectors.toSet());
  };

  public static final Function<RouteCalculation, RouteCalculationMode> toRouteCalculationMode = mode -> {
    if (mode == null) {
      return RouteCalculationMode.RANDOM;
    }

    switch (mode) {
      case NORMAL: return RouteCalculationMode.NORMAL;
      case OPTIMAL: return RouteCalculationMode.OPTIMAL;
      case RANDOM:
      default:
        return RouteCalculationMode.RANDOM;
    }
  };

  private static final Function<WaypointCategory, RouteWaypointCategory> toRouteWaypointCategory = category -> {
    switch (category) {
      case GAS_STATION: return RouteWaypointCategory.GAS_STATION;
      case PRIVATE: return RouteWaypointCategory.PRIVATE;
      case ALL: return RouteWaypointCategory.ALL;
      case APPOINTMENT:
      default:
        return RouteWaypointCategory.APPOINTMENT;
    }
  };

  private static final Function<RouteWaypointCategory, WaypointCategory> fromRouteWaypointCategory = category -> {
    switch (category) {
      case GAS_STATION: return WaypointCategory.GAS_STATION;
      case PRIVATE: return WaypointCategory.PRIVATE;
      case ALL: return WaypointCategory.ALL;
      case APPOINTMENT:
      default:
        return WaypointCategory.APPOINTMENT;
    }
  };

  private static final Function<WaypointStatus, RouteWaypointStatus> toRouteWaypointStatus = status -> {
    switch (status) {
      case FINISHED: return RouteWaypointStatus.FINISHED;
      case CANCELED: return RouteWaypointStatus.CANCELED;
      case ACTIVE: return RouteWaypointStatus.ACTIVE;
      case PENDING:
      default:
        return RouteWaypointStatus.PENDING;
    }
  };

  private static final Function<RouteWaypointStatus, WaypointStatus> fromRouteWaypointStatus = status -> {
    switch (status) {
      case FINISHED: return WaypointStatus.FINISHED;
      case CANCELED: return WaypointStatus.CANCELED;
      case ACTIVE: return WaypointStatus.ACTIVE;
      case PENDING:
      default:
        return WaypointStatus.PENDING;
    }
  };
}
