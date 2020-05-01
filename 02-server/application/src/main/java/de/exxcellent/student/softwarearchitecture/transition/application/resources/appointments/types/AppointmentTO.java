package de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentTO extends BusinessTO {

  private Long appointmentId;
  private String title;
  private String description;
  private AppointmentType appointmentType;
  private AppointmentPriority appointmentPriority;
  private String date;
  private Long travelDurationInSeconds;
  private String startTime;
  private String endTime;
  private Boolean finished;

  private Long inspectorId;
  private Long locationId;
  private Long contactId;

  public AppointmentTO() {
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AppointmentType getAppointmentType() {
    return appointmentType;
  }

  public void setAppointmentType(AppointmentType appointmentType) {
    this.appointmentType = appointmentType;
  }

  public AppointmentPriority getAppointmentPriority() {
    return appointmentPriority;
  }

  public void setAppointmentPriority(AppointmentPriority appointmentPriority) {
    this.appointmentPriority = appointmentPriority;
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

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }
}
