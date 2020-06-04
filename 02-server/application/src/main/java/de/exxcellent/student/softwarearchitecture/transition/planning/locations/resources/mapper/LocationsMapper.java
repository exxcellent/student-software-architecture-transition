package de.exxcellent.student.softwarearchitecture.transition.planning.locations.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.locations.resources.types.LocationTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.locations.resources.types.LocationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.api.types.LocationDO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class LocationsMapper {

  private LocationsMapper() {}

  public static final Function<List<LocationDO>, LocationsCTO> toLocationsCTO = locationDOList -> {
    var locationTOs = locationDOList.stream()
        .map(LocationsMapper.toLocationTO)
        .collect(Collectors.toList());

    var locationsCTO = new LocationsCTO();
    locationsCTO.setLocations(locationTOs);

    return locationsCTO;
  };

  public static final Function<LocationDO, LocationTO> toLocationTO = locationDO -> {
    var to = new LocationTO();

    to.setLocationId(locationDO.getLocationId());
    to.setVersion(locationDO.getVersion());
    to.setName(locationDO.getName());
    to.setStreet(locationDO.getStreet());
    to.setZip(locationDO.getZip());
    to.setCity(locationDO.getCity());
    to.setLatitude(locationDO.getLatitude());
    to.setLongitude(locationDO.getLongitude());

    return to;
  };

  public static final Function<LocationTO, LocationDO> toLocationDO = locationTO -> {
    var locationDO = new LocationDO();

    locationDO.setLocationId(locationTO.getLocationId());
    locationDO.setVersion(locationTO.getVersion());
    locationDO.setName(locationTO.getName());
    locationDO.setStreet(locationTO.getStreet());
    locationDO.setZip(locationTO.getZip());
    locationDO.setCity(locationTO.getCity());
    locationDO.setLatitude(locationTO.getLatitude());
    locationDO.setLongitude(locationTO.getLongitude());

    return locationDO;
  };
}
