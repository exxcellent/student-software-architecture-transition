package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.types.BusinessDO;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentDO extends BusinessDO {

  private Long appointmentId;
  private String title;
  private String description;
  private Type appointmentType;
  private Priority appointmentPriority;
  private LocalDate date;
  private Long travelDurationInSeconds;
  private LocalTime startTime;
  private LocalTime endTime;
  private Boolean finished;

  private Long inspectorId;
  private Long locationId;
  private Long contactId;

  public AppointmentDO() {
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

  public Type getAppointmentType() {
    return appointmentType;
  }

  public void setAppointmentType(Type appointmentType) {
    this.appointmentType = appointmentType;
  }

  public Priority getAppointmentPriority() {
    return appointmentPriority;
  }

  public void setAppointmentPriority(Priority appointmentPriority) {
    this.appointmentPriority = appointmentPriority;
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

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
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
