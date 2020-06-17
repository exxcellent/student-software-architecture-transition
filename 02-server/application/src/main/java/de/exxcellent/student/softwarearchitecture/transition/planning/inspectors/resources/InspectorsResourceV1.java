package de.exxcellent.student.softwarearchitecture.transition.planning.inspectors.resources;

import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspectors.resources.mapper.InspectorsMapper;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspectors.resources.types.InspectorTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspectors.resources.types.InspectorsCTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspector.api.InspectorComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.resources.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.SecuredResource;
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
@RequestMapping("v1/inspectors")
public class InspectorsResourceV1 extends SecuredResource implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(InspectorsResourceV1.class);

  private final InspectorComponent inspectorComponent;

  @Autowired
  public InspectorsResourceV1(InspectorComponent inspectorComponent) {
    this.inspectorComponent = inspectorComponent;
  }


  @Operation(summary = "Find All Inspectors")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public InspectorsCTO findAll() {
    var inspectorList = inspectorComponent.findAll();
    return InspectorsMapper.toInspectorsCTO.apply(inspectorList);
  }

  @Operation(summary = "Find Inspector")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{inspectorId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public InspectorTO findById(@PathVariable("inspectorId") Long inspectorId) {
    return InspectorsMapper.toInspectorTO.apply(inspectorComponent.findById(inspectorId));
  }

  @Operation(summary = "Create Inspector")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ResponseEntity<InspectorTO> create(@RequestBody InspectorTO inspectorTO) {
    var inspectorDO = InspectorsMapper.toInspectorDO.apply(inspectorTO);
    var createdInspectorDO = inspectorComponent.create(inspectorDO, CurrentUser.getUser());
    var response = InspectorsMapper.toInspectorTO.apply(createdInspectorDO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Update Inspector")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public InspectorTO update(@RequestBody InspectorTO inspectorTO) {
    var inspectorDO = InspectorsMapper.toInspectorDO.apply(inspectorTO);
    var updatedInspectorDO = inspectorComponent.update(inspectorDO, CurrentUser.getUser());
    return InspectorsMapper.toInspectorTO.apply(updatedInspectorDO);
  }

  @Operation(summary = "Delete Inspector")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{id}",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
    inspectorComponent.delete(id);
    return ResponseEntity.ok().build();
  }
}
