package de.exxcellent.student.softwarearchitecture.transition.application.resources.processs;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.processs.types.ProcessTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.processs.types.ProcesssCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.processs.mapper.ProcessMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.api.ProcessComponent;
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
@RequestMapping("v1/processes")
public class ProcessesResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessesResourceV1.class);

  private final ProcessComponent processComponent;

  @Autowired
  public ProcessesResourceV1(ProcessComponent processComponent) {
    this.processComponent = processComponent;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ProcesssCTO findAll() {
    var processList = processComponent.findAll();
    return ProcessMapper.toProcesssCTO.apply(processList);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @RequiresPermission(Permission.READ_ALL)
  public ProcessTO findById(@PathVariable("id") Long id) {
    return ProcessMapper.toProcessTO.apply(processComponent.findById(id));
  }

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

  @RequestMapping(
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @RequiresPermission(Permission.DELETE_ALL)
  public ResponseEntity<String> delete(@RequestBody ProcessTO processTO) {
    var locationDO = ProcessMapper.toProcessDO.apply(processTO);
    processComponent.delete(locationDO);
    return ResponseEntity.ok().build();
  }
}
