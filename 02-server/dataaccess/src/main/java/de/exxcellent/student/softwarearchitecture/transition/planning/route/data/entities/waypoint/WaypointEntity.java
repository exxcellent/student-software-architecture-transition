package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.waypoint;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.CommonEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "waypoint")
@Entity
public class WaypointEntity extends CommonEntity {

  @Column(name = "appointment_id")
  private Long appointmentId;

  @Column(name = "inspector_id")
  private Long inspectorId;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "order_index")
  private Integer orderIndex;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(name = "address")
  private String address;

  @Column(name = "latitude")
  private Float latitude;

  @Column(name = "longitude")
  private Float longitude;

  @Column(name = "travel_duration_sec")
  private Long travelDuration;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "duration_sec")
  private Long duration;

  @Column(name = "contact_name")
  private String contactname;

  @Column(name = "contact_phone_number")
  private String contactPhoneNumber;

  @Column(name = "contact_email")
  private String contactEmail;

  public WaypointEntity() {
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Integer getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(Integer orderIndex) {
    this.orderIndex = orderIndex;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
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

  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }

  public String getContactname() {
    return contactname;
  }

  public void setContactname(String contactname) {
    this.contactname = contactname;
  }

  public String getContactPhoneNumber() {
    return contactPhoneNumber;
  }

  public void setContactPhoneNumber(String contactPhoneNumber) {
    this.contactPhoneNumber = contactPhoneNumber;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }
}
