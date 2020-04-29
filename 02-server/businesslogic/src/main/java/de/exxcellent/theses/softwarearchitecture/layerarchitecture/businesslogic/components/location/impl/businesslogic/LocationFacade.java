package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.businesslogic;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.User;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.validation.Preconditions;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api.LocationComponent;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api.types.LocationDO;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.businesslogic.logic.LocationLogic;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.LocationRepository;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.businesslogic.mapper.LocationMapper;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class LocationFacade implements LocationComponent {

  private LocationLogic locationLogic;

  @Autowired
  public LocationFacade(LocationLogic locationLogic) {
    this.locationLogic = locationLogic;
  }

  @Override
  public List<LocationDO> findAll() {
    var locations = locationLogic.findAll();

    return locations.stream()
        .map(LocationMapper.toLocationDO)
        .collect(Collectors.toList());
  }

  @Override
  public LocationDO findById(Long locationId) {
    Preconditions.checkNotNull(locationId, "LocationId must not be null");
    Preconditions.checkArgument(locationId > 0, "LocationId must be positive");

    return LocationMapper.toLocationDO.apply(locationLogic.findById(locationId));
  }

  @Override
  public LocationDO create(LocationDO newLocationDO, User user) {
    Preconditions.checkNotNull(newLocationDO, "LocationDO must not be null");
    Preconditions.checkNotNull(newLocationDO.getLatitude(), "Latitude must not be null");
    Preconditions.checkNotNull(newLocationDO.getLongitude(), "Longitude must not be null");
    Preconditions.checkNotNull(newLocationDO.getStreet(), "Street must not be null");
    Preconditions.checkNotNull(newLocationDO.getZip(), "Zip must not be null");
    Preconditions.checkNotNull(newLocationDO.getCity(), "City must not be null");

    var newLocationEntity = LocationMapper.toLocationEntity.apply(newLocationDO);
    var persistedLocationEntity = locationLogic.create(newLocationEntity, user);

    return LocationMapper.toLocationDO.apply(persistedLocationEntity);
  }

  @Override
  public LocationDO update(LocationDO locationDO, User user) {
    Preconditions.checkNotNull(locationDO, "LocationDO must not be null");
    Preconditions.checkNotNull(locationDO.getLocationId(), "LocationId must not be null");
    Preconditions.checkNotNull(locationDO.getVersion(), "Version must not be null");
    Preconditions.checkNotNull(locationDO.getLatitude(), "Latitude must not be null");
    Preconditions.checkNotNull(locationDO.getLongitude(), "Longitude must not be null");
    Preconditions.checkNotNull(locationDO.getStreet(), "Street must not be null");
    Preconditions.checkNotNull(locationDO.getZip(), "Zip must not be null");
    Preconditions.checkNotNull(locationDO.getCity(), "City must not be null");

    var locationEntity = LocationMapper.toLocationEntity.apply(locationDO);
    var persistedLocationEntity = locationLogic.update(locationEntity, user);

    return LocationMapper.toLocationDO.apply(persistedLocationEntity);
  }

  @Override
  public void delete(LocationDO locationDO) {
    Preconditions.checkNotNull(locationDO, "LocationDO must not be null");
    Preconditions.checkNotNull(locationDO.getLocationId(), "LocationId must not be null");
    Preconditions.checkNotNull(locationDO.getVersion(), "Version must not be null");

    var locationEntity = LocationMapper.toLocationEntity.apply(locationDO);

    locationLogic.delete(locationEntity);
  }
}
