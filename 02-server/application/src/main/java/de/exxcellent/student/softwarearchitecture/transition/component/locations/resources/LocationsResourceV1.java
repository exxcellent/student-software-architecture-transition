package de.exxcellent.student.softwarearchitecture.transition.component.locations.resources;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.SecuredResource;
import de.exxcellent.student.softwarearchitecture.transition.component.locations.resources.mapper.LocationsMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.locations.resources.types.LocationTO;
import de.exxcellent.student.softwarearchitecture.transition.component.locations.resources.types.LocationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.location.api.LocationComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.component.user.resources.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Planning")
@RestController
@CrossOrigin
@RequestMapping("v1/locations")
public class LocationsResourceV1 extends SecuredResource implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(LocationsResourceV1.class);

  private final LocationComponent locationComponent;

  @Autowired
  public LocationsResourceV1(LocationComponent locationComponent) {
    this.locationComponent = locationComponent;
  }

  @Operation(summary = "Find All Locations")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public LocationsCTO findAll() {
    var locationList = locationComponent.findAll();
    return LocationsMapper.toLocationsCTO.apply(locationList);
  }

  @Operation(summary = "Find Location")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{locationId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public LocationTO findById(@PathVariable("locationId") Long locationId) {
    return LocationsMapper.toLocationTO.apply(locationComponent.findById(locationId));
  }

  @Operation(summary = "Create Location")
  @SecurityRequirement(name = "jwtAuth")
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

  @Operation(summary = "Update Location")
  @SecurityRequirement(name = "jwtAuth")
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

  @Operation(summary = "Delete Location")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{id}",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
    locationComponent.delete(id);
    return ResponseEntity.ok().build();
  }
}
