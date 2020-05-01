package de.exxcellent.student.softwarearchitecture.transition.application.resources.locations;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.locations.mapper.LocationsMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.locations.types.LocationTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.locations.types.LocationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.api.LocationComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@RestController
@CrossOrigin
@RequestMapping("v1/locations")
public class LocationsResourceV1 implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(LocationsResourceV1.class);

  private final LocationComponent locationComponent;

  @Autowired
  public LocationsResourceV1(LocationComponent locationComponent) {
    this.locationComponent = locationComponent;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public LocationsCTO findAll() {
    var locationList = locationComponent.findAll();
    return LocationsMapper.toLocationsCTO.apply(locationList);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{locationId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public LocationTO findById(@PathVariable("locationId") Long locationId) {
    return LocationsMapper.toLocationTO.apply(locationComponent.findById(locationId));
  }

  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ResponseEntity<LocationTO> create(@RequestBody LocationTO locationTO) {
    var locationDO = LocationsMapper.toLocationDO.apply(locationTO);
    var createdLocationDO = locationComponent.create(locationDO, CurrentUser.getUser());
    var response = LocationsMapper.toLocationTO.apply(createdLocationDO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public LocationTO update(@RequestBody LocationTO locationTO) {
    var locationDO = LocationsMapper.toLocationDO.apply(locationTO);
    var updatedLocationDO = locationComponent.update(locationDO, CurrentUser.getUser());
    return LocationsMapper.toLocationTO.apply(updatedLocationDO);
  }

  @RequestMapping(
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @RequiresPermission(Permission.DELETE_ALL)
  public ResponseEntity<String> delete(@RequestBody LocationTO locationTO) {
    var locationDO = LocationsMapper.toLocationDO.apply(locationTO);
    locationComponent.delete(locationDO);
    return ResponseEntity.ok().build();
  }
}
