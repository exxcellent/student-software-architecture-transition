package de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.entities.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "appointment")
@Entity
public class AppointmentEntity extends CommonEntity {

  @Column(name = "process_id")
  private Long processId;

  @Column(name = "description")
  private String description;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "travel_duration_sec")
  private Long travelDuration;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "appointment_duration_sec")
  private Long appointmentDuration;

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
