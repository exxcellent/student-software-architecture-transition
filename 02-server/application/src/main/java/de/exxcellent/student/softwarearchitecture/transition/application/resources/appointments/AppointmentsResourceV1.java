package de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.mapper.AppointmentMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.AppointmentComponent;
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
@RequestMapping("v1/appointments")
public class AppointmentsResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(AppointmentsResourceV1.class);

  private final AppointmentComponent appointmentComponent;

  @Autowired
  public AppointmentsResourceV1(AppointmentComponent appointmentComponent) {
    this.appointmentComponent = appointmentComponent;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentsCTO findAll() {
    var appointmentList = appointmentComponent.findAll();
    return AppointmentMapper.toAppointmentsCTO.apply(appointmentList);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentTO findById(@PathVariable("id") Long id) {
    return AppointmentMapper.toAppointmentTO.apply(appointmentComponent.findById(id));
  }

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

  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AppointmentTO update(@RequestBody AppointmentTO appointmentTO) {
    var appointmentDO = AppointmentMapper.toAppointmentDO.apply(appointmentTO);
    var updatedAppointmentDO = appointmentComponent.update(appointmentDO, CurrentUser.getUser());
    return AppointmentMapper.toAppointmentTO.apply(updatedAppointmentDO);
  }

  @RequestMapping(
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@RequestBody AppointmentTO appointmentTO) {
    var locationDO = AppointmentMapper.toAppointmentDO.apply(appointmentTO);
    appointmentComponent.delete(locationDO);
    return ResponseEntity.ok().build();
  }
}
