package de.exxcellent.student.softwarearchitecture.transition.component.location.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.location.api.types.LocationDO;
import de.exxcellent.student.softwarearchitecture.transition.component.location.dataaccess.types.LocationDTO;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class LocationMapper {

  private LocationMapper() {}


  public static final Function<LocationDTO, LocationDO> toLocationDO = entity -> {
    var locationDO = new LocationDO();

    locationDO.setLocationId(entity.getId());
    locationDO.setVersion(entity.getVersion());
    locationDO.setName(entity.getName());
    locationDO.setStreet(entity.getStreet());
    locationDO.setZip(entity.getZip());
    locationDO.setCity(entity.getCity());
    locationDO.setLatitude(entity.getLatitude());
    locationDO.setLongitude(entity.getLongitude());

    return locationDO;
  };

  public static final Function<LocationDO, LocationDTO> toLocationEntity = locationDO -> {
    var LocationDTO = new LocationDTO();

    LocationDTO.setId(locationDO.getLocationId());
    LocationDTO.setVersion(locationDO.getVersion());
    LocationDTO.setName(locationDO.getName());
    LocationDTO.setStreet(locationDO.getStreet());
    LocationDTO.setZip(locationDO.getZip());
    LocationDTO.setCity(locationDO.getCity());
    LocationDTO.setLatitude(locationDO.getLatitude());
    LocationDTO.setLongitude(locationDO.getLongitude());

    return LocationDTO;
  };
}
