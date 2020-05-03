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

  @Column(name = "process_id")
  private Long processId;

  @Column(name = "description")
  private String description;

  @Column(name = "date")
  private LocalDate date;

  @Column(
      name = "travel_duration",
      columnDefinition = "interval"
  )
  private Duration travelDuration;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(
      name = "appointment_duration",
      columnDefinition = "interval"
  )  private Duration appointmentDuration;

  @Column(name = "finished")
  private Boolean finished;


  public AppointmentEntity() {
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

  public Duration getAppointmentDuration() {
    return appointmentDuration;
  }

  public void setAppointmentDuration(Duration appointmentDuration) {
    this.appointmentDuration = appointmentDuration;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }
}
