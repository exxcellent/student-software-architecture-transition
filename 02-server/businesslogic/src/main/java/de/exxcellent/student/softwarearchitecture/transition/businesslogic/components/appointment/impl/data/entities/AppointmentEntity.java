package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities;

import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.entities.CommonEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.data.entities.InspectorEntity;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "appointment")
@Entity
@TypeDef(
    typeClass = PostgreSQLIntervalType.class,
    defaultForType = Duration.class
)
public class AppointmentEntity extends CommonEntity {

  @Column(name = "inspector_id")
  private Long inspectorId;

  @Column(name = "location_id")
  private Long locationId;

  @Column(name = "contact_id")
  private Long contactId;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private AppointmentType appointmentType;

  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  private AppointmentPriority appointmentPriority;

  @Column(name = "date")
  private LocalDate date;

  @Column(
      name = "travel_duration",
      columnDefinition = "interval"
  )
  private Duration travelDuration;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "finished")
  private Boolean finished;


  public AppointmentEntity() {
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Duration getTravelDuration() {
    return travelDuration;
  }

  public void setTravelDuration(Duration travelDuration) {
    this.travelDuration = travelDuration;
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
