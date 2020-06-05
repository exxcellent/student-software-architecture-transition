package de.exxcellent.student.softwarearchitecture.transition.component.location.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.location.api.types.LocationDO;
import de.exxcellent.student.softwarearchitecture.transition.component.location.data.entities.LocationEntity;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class LocationMapper {

  private LocationMapper() {}


  public static final Function<LocationEntity, LocationDO> toLocationDO = entity -> {
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

  public static final Function<LocationDO, LocationEntity> toLocationEntity = locationDO -> {
    var locationEntity = new LocationEntity();

    locationEntity.setId(locationDO.getLocationId());
    locationEntity.setVersion(locationDO.getVersion());
    locationEntity.setName(locationDO.getName());
    locationEntity.setStreet(locationDO.getStreet());
    locationEntity.setZip(locationDO.getZip());
    locationEntity.setCity(locationDO.getCity());
    locationEntity.setLatitude(locationDO.getLatitude());
    locationEntity.setLongitude(locationDO.getLongitude());

    return locationEntity;
  };
}
