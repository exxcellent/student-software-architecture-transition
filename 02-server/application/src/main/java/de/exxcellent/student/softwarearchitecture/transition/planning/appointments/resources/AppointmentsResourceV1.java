package de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.mapper.AppointmentMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentsCTO;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.SecuredResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.api.AppointmentComponent;
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
@RequestMapping("v1/appointments")
public class AppointmentsResourceV1 extends SecuredResource implements BusinessResource {

  private static final Logger LOG = LoggerFactory.getLogger(AppointmentsResourceV1.class);

  private final AppointmentComponent appointmentComponent;

  @Autowired
  public AppointmentsResourceV1(AppointmentComponent appointmentComponent) {
    this.appointmentComponent = appointmentComponent;
  }

  @Operation(summary = "Find All Appointments")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentsCTO findAll() {
    var appointmentList = appointmentComponent.findAll();
    return AppointmentMapper.toAppointmentsCTO.apply(appointmentList);
  }

  @Operation(summary = "Find All Appointments")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentTO findById(@PathVariable("id") Long id) {
    return AppointmentMapper.toAppointmentTO.apply(appointmentComponent.findById(id));
  }

  @Operation(summary = "Create new Appointment")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppointmentTO> create(@RequestBody AppointmentTO appointmentTO) {
    var appointmentDO = AppointmentMapper.toAppointmentDO.apply(appointmentTO);
    var createdAppointmentDO = appointmentComponent.create(appointmentDO, CurrentUser.getUser());
    var response = AppointmentMapper.toAppointmentTO.apply(createdAppointmentDO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Update Appointment")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentTO update(@RequestBody AppointmentTO appointmentTO) {
    var appointmentDO = AppointmentMapper.toAppointmentDO.apply(appointmentTO);
    var updatedAppointmentDO = appointmentComponent.update(appointmentDO, CurrentUser.getUser());
    return AppointmentMapper.toAppointmentTO.apply(updatedAppointmentDO);
  }

  @Operation(summary = "Delete Appointment")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{id}",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long appointmentId) {
    appointmentComponent.delete(appointmentId);
    return ResponseEntity.ok().build();
  }
}
