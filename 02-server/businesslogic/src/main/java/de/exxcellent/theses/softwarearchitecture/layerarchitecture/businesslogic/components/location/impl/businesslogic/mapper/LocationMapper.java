package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.businesslogic.mapper;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api.types.LocationDO;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.entities.LocationEntity;

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

    locationDO.setLocationId(entity.getLocationId());
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

    locationEntity.setLocationId(locationDO.getLocationId());
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
