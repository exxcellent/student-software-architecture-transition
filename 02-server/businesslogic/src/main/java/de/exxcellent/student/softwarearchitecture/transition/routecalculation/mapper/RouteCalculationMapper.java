package de.exxcellent.student.softwarearchitecture.transition.routecalculation.mapper;

import de.exxcellent.student.softwarearchitecture.transition.routecalculation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.routecalculation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.routecalculation.api.types.LocationResponseDO;
import de.exxcellent.student.softwarearchitecture.transition.routecalculation.api.types.RouteCalculationDO;
import de.exxcellent.student.softwarearchitecture.transition.routecalculation.connector.types.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class RouteCalculationMapper {

  private RouteCalculationMapper() {}

  public static final Function<List<LocationRequestDO>, TripTO> toTripTO = input -> {
    var tripTO = new TripTO();

    var locations = input.stream()
        .map(RouteCalculationMapper.toLocationTO)
        .collect(Collectors.toList());

    tripTO.setDeparture_time("2020-06-04T12:49:22.257Z");
    tripTO.setLocations(locations);

    return tripTO;
  };

  public static final Function<TripResponseTO, RouteCalculationDO> toRouteCalculationDO = input -> {
    var routeCalculationDO = new RouteCalculationDO();

    var travelDuration = Math.round(input.getTravel_time() * 60); // minutes -> seconds
    var locations = input.getLocations().stream()
        .map(RouteCalculationMapper.toLocationResponseDO)
        .collect(Collectors.toList());

    routeCalculationDO.setSortedPositions(locations);
    routeCalculationDO.setTravelDurationInSeconds((long) travelDuration);

    return routeCalculationDO;
  };

  public static final Function<LocationRequestDO, LocationTO> toLocationTO = input -> {
    var locationTO = new LocationTO();

    locationTO.setId((int) input.getId());
    locationTO.set_index(input.getIndex());
    locationTO.setLat(input.getLatitude());
    locationTO.setLng(input.getLongitude());

    return locationTO;
  };

  public static final Function<LocationResponseTO, LocationResponseDO> toLocationResponseDO = input -> {
    var locationDO = new LocationResponseDO();

    var travelDuration = Math.round(input.getTravel_time() * 60); // minutes -> seconds

    locationDO.setId(input.getId());
    locationDO.setIndex(input.get_index());
    locationDO.setLatitude(input.getLat());
    locationDO.setLongitude(input.getLng());
    locationDO.setTravelDuration((long) travelDuration);

    return locationDO;
  };


  public static final Function<CalculationMode, Mode> toMode = mode -> {
    switch (mode) {
      case NORMAL: return Mode.NONE;
      case OPTIMAL: return Mode.BRUTE_FORCE;
      case RANDOM:
      default:
        return Mode.RANDOM;
    }
  };
}
