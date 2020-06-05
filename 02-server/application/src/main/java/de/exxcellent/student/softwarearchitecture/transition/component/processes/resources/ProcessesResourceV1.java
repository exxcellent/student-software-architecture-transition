package de.exxcellent.student.softwarearchitecture.transition.component.processes.resources;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.SecuredResource;
import de.exxcellent.student.softwarearchitecture.transition.component.user.resources.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcessTO;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcesssCTO;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.mapper.ProcessMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.process.api.ProcessComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.user.api.types.Permission;
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
@RequestMapping("v1/processes")
public class ProcessesResourceV1 extends SecuredResource implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessesResourceV1.class);

  private final ProcessComponent processComponent;

  @Autowired
  public ProcessesResourceV1(ProcessComponent processComponent) {
    this.processComponent = processComponent;
  }

  @Operation(summary = "Find All Processes")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ProcesssCTO findAll() {
    var processList = processComponent.findAll();
    return ProcessMapper.toProcesssCTO.apply(processList);
  }

  @Operation(summary = "Find Process")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ProcessTO findById(@PathVariable("id") Long id) {
    return ProcessMapper.toProcessTO.apply(processComponent.findById(id));
  }

  @Operation(summary = "Create Process")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ResponseEntity<ProcessTO> create(@RequestBody ProcessTO processTO) {
    var processDO = ProcessMapper.toProcessDO.apply(processTO);
    var createdProcessDO = processComponent.create(processDO, CurrentUser.getUser());
    var response = ProcessMapper.toProcessTO.apply(createdProcessDO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Update Process")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.WRITE_ALL)
  public ProcessTO update(@RequestBody ProcessTO processTO) {
    var processDO = ProcessMapper.toProcessDO.apply(processTO);
    var updatedProcessDO = processComponent.update(processDO, CurrentUser.getUser());
    return ProcessMapper.toProcessTO.apply(updatedProcessDO);
  }

  @Operation(summary = "Delete Process")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{id}",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
    processComponent.delete(id);
    return ResponseEntity.ok().build();
  }
}
