package de.exxcellent.student.softwarearchitecture.transition.navigation.route.mapper;

import de.exxcellent.student.softwarearchitecture.transition.navigation.route.api.types.route.*;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.Category;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.Status;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.WaypointDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.LocationRequestDO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class RouteMapper {

  private RouteMapper() {}

  public static final Function<WaypointDTO, WaypointDO> toWaypointDO = entity -> {
    if (entity == null) {
      return null;
    }

    var waypointDO = new WaypointDO();

    waypointDO.setWaypointId(entity.getId());
    waypointDO.setVersion(entity.getVersion());
    waypointDO.setInspectorId(entity.getInspectorId());
    waypointDO.setAppointmentId(entity.getAppointmentId());
    waypointDO.setDate(entity.getDate());
    waypointDO.setOrderIndex(entity.getOrderIndex());

    waypointDO.setCategory(RouteMapper.toWaypointCategory.apply(entity.getCategory()));
    waypointDO.setStatus(RouteMapper.toWaypointStatus.apply(entity.getStatus()));
    waypointDO.setStartTime(entity.getStartTime());
    if (entity.getTravelDuration() != null) {
      waypointDO.setTravelDuration(Duration.of(entity.getTravelDuration(), ChronoUnit.SECONDS));
    }
    if (entity.getDuration() != null) {
      waypointDO.setDuration(Duration.of(entity.getDuration(), ChronoUnit.SECONDS));
    }

    waypointDO.setAddress(entity.getAddress());
    waypointDO.setLatitude(entity.getLatitude());
    waypointDO.setLongitude(entity.getLongitude());

    waypointDO.setContactName(entity.getContactname());
    waypointDO.setPhoneNumber(entity.getContactPhoneNumber());
    waypointDO.setEmail(entity.getContactEmail());

    return waypointDO;
  };

  public static final Function<WaypointDO, WaypointDTO> toWaypointEntity = waypoint -> {
    var WaypointDTO = new WaypointDTO();

    WaypointDTO.setId(waypoint.getWaypointId());
    WaypointDTO.setVersion(waypoint.getVersion());
    WaypointDTO.setInspectorId(waypoint.getInspectorId());
    WaypointDTO.setAppointmentId(waypoint.getAppointmentId());
    WaypointDTO.setDate(waypoint.getDate());
    WaypointDTO.setOrderIndex(waypoint.getOrderIndex());

    WaypointDTO.setCategory(RouteMapper.fromWaypointCategory.apply(waypoint.getCategory()));
    WaypointDTO.setStatus(RouteMapper.fromWaypointStatus.apply(waypoint.getStatus()));

    if (waypoint.getStartTime() != null) {
      WaypointDTO.setStartTime(waypoint.getStartTime());
    }
    if (waypoint.getTravelDuration() != null) {
      WaypointDTO.setTravelDuration(waypoint.getTravelDuration().toSeconds());
    }
    if (waypoint.getDuration() != null) {
      WaypointDTO.setDuration(waypoint.getDuration().toSeconds());
    }

    WaypointDTO.setAddress(waypoint.getAddress());
    WaypointDTO.setLatitude(waypoint.getLatitude());
    WaypointDTO.setLongitude(waypoint.getLongitude());

    WaypointDTO.setContactname(waypoint.getContactName());
    WaypointDTO.setContactPhoneNumber(waypoint.getPhoneNumber());
    WaypointDTO.setContactEmail(waypoint.getEmail());

    return WaypointDTO;
  };

  public static final Function<List<WaypointDO>, RouteDO> toRouteDO = waypoints -> {
    var routeDO = new RouteDO();

    LocalDate date = null;
    if (!waypoints.isEmpty()) {
      date = waypoints.get(0).getDate();
    }

    routeDO.setDate(date);
    routeDO.setWaypoints(waypoints);

    return routeDO;
  };



  public static final Function<List<WaypointDO>, List<LocationRequestDO>> toLocationRequestDOList = waypoints ->
      waypoints.stream().map(RouteMapper.toLocationRequestDO).collect(Collectors.toList());

  public static final Function<WaypointDO, LocationRequestDO> toLocationRequestDO = waypoint -> {
    var locationRequestDO = new LocationRequestDO();

    locationRequestDO.setId(waypoint.getWaypointId());
    locationRequestDO.setIndex(waypoint.getOrderIndex());
    locationRequestDO.setLatitude(waypoint.getLatitude());
    locationRequestDO.setLongitude(waypoint.getLongitude());

    return locationRequestDO;
  };




  public static final Function<RouteCalculationMode, CalculationMode> toCalculationMode = mode -> {
    switch (mode) {
      case NORMAL: return CalculationMode.NORMAL;
      case OPTIMAL: return CalculationMode.OPTIMAL;
      case RANDOM:
      default:
        return CalculationMode.RANDOM;
    }
  };

  private static final Function<Category, WaypointCategory> toWaypointCategory = category -> {
    switch (category) {
      case PRIVATE: return WaypointCategory.PRIVATE;
      case GAS_STATION: return WaypointCategory.GAS_STATION;
      case APPOINTMENT:
      default:
        return WaypointCategory.APPOINTMENT;
    }
  };

  private static final Function<WaypointCategory, Category> fromWaypointCategory = category -> {
    switch (category) {
      case PRIVATE: return Category.PRIVATE;
      case GAS_STATION: return Category.GAS_STATION;
      case APPOINTMENT:
      default:
        return Category.APPOINTMENT;
    }
  };

  private static final Function<Status, WaypointStatus> toWaypointStatus = status -> {
    switch (status) {
      case ACTIVE: return WaypointStatus.ACTIVE;
      case FINISHED: return WaypointStatus.FINISHED;
      case CANCELED: return WaypointStatus.CANCELED;
      case PENDING:
      default:
        return WaypointStatus.PENDING;
    }
  };

  private static final Function<WaypointStatus, Status> fromWaypointStatus = status -> {
    switch (status) {
      case ACTIVE: return Status.ACTIVE;
      case FINISHED: return Status.FINISHED;
      case CANCELED: return Status.CANCELED;
      case PENDING:
      default:
        return Status.PENDING;
    }
  };
}
