package de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors.types.InspectorTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors.mapper.InspectorsMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors.types.InspectorsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.api.InspectorComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
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
@RequestMapping("v1/inspectors")
public class InspectorsResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(InspectorsResourceV1.class);

  private final InspectorComponent inspectorComponent;

  @Autowired
  public InspectorsResourceV1(InspectorComponent inspectorComponent) {
    this.inspectorComponent = inspectorComponent;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public InspectorsCTO findAll() {
    var inspectorList = inspectorComponent.findAll();
    return InspectorsMapper.toInspectorsCTO.apply(inspectorList);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{inspectorId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public InspectorTO findById(@PathVariable("inspectorId") Long inspectorId) {
    return InspectorsMapper.toInspectorTO.apply(inspectorComponent.findById(inspectorId));
  }

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

  @RequestMapping(
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @RequiresPermission(Permission.DELETE_ALL)
  public ResponseEntity<String> delete(@RequestBody InspectorTO inspectorTO) {
    var locationDO = InspectorsMapper.toInspectorDO.apply(inspectorTO);
    inspectorComponent.delete(locationDO);
    return ResponseEntity.ok().build();
  }
}