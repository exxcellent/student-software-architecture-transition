package de.exxcellent.student.softwarearchitecture.transition.planning.location.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.api.LocationComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.api.types.LocationDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.logic.LocationLogic;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.mapper.LocationMapper;
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
    Preconditions.checkNull(newLocationDO.getLocationId(), "LocationId must be null");
    Preconditions.checkNull(newLocationDO.getVersion(), "Location version must be null");

    Preconditions.checkNotNull(newLocationDO.getLatitude(), "Latitude must not be null");
    Preconditions.checkNotNull(newLocationDO.getLongitude(), "Longitude must not be null");
    Preconditions.checkNotNull(newLocationDO.getStreet(), "Street must not be null");
    Preconditions.checkNotNull(newLocationDO.getZip(), "Zip must not be null");
    Preconditions.checkNotNull(newLocationDO.getCity(), "City must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var newLocationEntity = LocationMapper.toLocationEntity.apply(newLocationDO);
    var persistedLocationEntity = locationLogic.create(newLocationEntity, user);

    return LocationMapper.toLocationDO.apply(persistedLocationEntity);
  }

  @Override
  public LocationDO update(LocationDO locationDO, User user) {
    Preconditions.checkNotNull(locationDO, "LocationDO must not be null");
    Preconditions.checkNotNull(locationDO.getLocationId(), "LocationId must not be null");
    Preconditions.checkArgument(locationDO.getLocationId() > 0, "LocationId must be positive");
    Preconditions.checkNotNull(locationDO.getVersion(), "Location version must not be null");
    Preconditions.checkArgument(locationDO.getVersion() >= 0, "Location version must be positive");
    Preconditions.checkNotNull(locationDO.getLatitude(), "Latitude must not be null");
    Preconditions.checkNotNull(locationDO.getLongitude(), "Longitude must not be null");
    Preconditions.checkNotNull(locationDO.getStreet(), "Street must not be null");
    Preconditions.checkNotNull(locationDO.getZip(), "Zip must not be null");
    Preconditions.checkNotNull(locationDO.getCity(), "City must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var locationEntity = LocationMapper.toLocationEntity.apply(locationDO);
    var persistedLocationEntity = locationLogic.update(locationEntity, user);

    return LocationMapper.toLocationDO.apply(persistedLocationEntity);
  }

  @Override
  public void delete(Long id) {
    Preconditions.checkNotNull(id, "LocationId must not be null");
    Preconditions.checkArgument(id > 0, "LocationId must be positive");

    locationLogic.delete(id);
  }
}
