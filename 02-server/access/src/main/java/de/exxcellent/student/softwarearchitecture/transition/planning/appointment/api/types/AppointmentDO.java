package de.exxcellent.student.softwarearchitecture.transition.planning.appointment.api.types;

import de.exxcellent.student.softwarearchitecture.transition.common.types.BusinessDO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentDO extends BusinessDO {

  private Long appointmentId;
  private String description;
  private LocalDate date;
  private Long travelDurationInSeconds;
  private LocalTime startTime;
  private Long durationInSeconds;
  private Boolean finished;

  private Long processId;

  public AppointmentDO() {
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
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

  public Long getTravelDurationInSeconds() {
    return travelDurationInSeconds;
  }

  public void setTravelDurationInSeconds(Long travelDurationInSeconds) {
    this.travelDurationInSeconds = travelDurationInSeconds;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public Long getDurationInSeconds() {
    return durationInSeconds;
  }

  public void setDurationInSeconds(Long durationInSeconds) {
    this.durationInSeconds = durationInSeconds;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public Long getProcessId() {
    return processId;
  }

  public void setProcessId(Long processId) {
    this.processId = processId;
  }
}
