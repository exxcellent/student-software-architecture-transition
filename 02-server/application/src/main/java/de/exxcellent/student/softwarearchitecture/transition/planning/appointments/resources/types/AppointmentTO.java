package de.exxcellent.student.softwarearchitecture.transition.planning.appointments.resources.types;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentTO extends BusinessTO {

  private Long appointmentId;
  private String description;
  private String date;
  private Long travelDurationInSeconds;
  private String startTime;
  private Long durationInSeconds;
  private Boolean finished;

  private Long processId;

  public AppointmentTO() {
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Long getTravelDurationInSeconds() {
    return travelDurationInSeconds;
  }

  public void setTravelDurationInSeconds(Long travelDurationInSeconds) {
    this.travelDurationInSeconds = travelDurationInSeconds;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
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
