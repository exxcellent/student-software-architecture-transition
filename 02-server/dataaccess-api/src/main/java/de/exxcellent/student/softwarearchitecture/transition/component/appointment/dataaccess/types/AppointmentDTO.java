package de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CommonDTO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentDTO extends CommonDTO {

  private Long processId;

  private String description;

  private LocalDate date;

  private Long travelDuration;

  private LocalTime startTime;

  private Long appointmentDuration;

  private Boolean finished;


  public AppointmentDTO() {
  }

  public Long getProcessId() {
    return processId;
  }

  public void setProcessId(Long processId) {
    this.processId = processId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getTravelDuration() {
    return travelDuration;
  }

  public void setTravelDuration(Long travelDuration) {
    this.travelDuration = travelDuration;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public Long getAppointmentDuration() {
    return appointmentDuration;
  }

  public void setAppointmentDuration(Long appointmentDuration) {
    this.appointmentDuration = appointmentDuration;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }
}
