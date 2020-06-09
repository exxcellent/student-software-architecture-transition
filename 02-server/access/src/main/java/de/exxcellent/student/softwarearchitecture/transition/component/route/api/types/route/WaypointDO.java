package de.exxcellent.student.softwarearchitecture.transition.component.route.api.types.route;

import de.exxcellent.student.softwarearchitecture.transition.common.types.BusinessDO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class WaypointDO extends BusinessDO {

  private Long waypointId;
  private LocalDate date;
  private Integer orderIndex;
  private WaypointCategory category;
  private WaypointStatus status;
  private Duration travelDuration;
  private LocalTime startTime;
  private Duration duration;

  private Long inspectorId;
  private Long appointmentId;

  private String address;
  private Float latitude;
  private Float longitude;

  private String contactName;
  private String phoneNumber;
  private String email;

  public WaypointDO() {
  }

  public Long getWaypointId() {
    return waypointId;
  }

  public void setWaypointId(Long waypointId) {
    this.waypointId = waypointId;
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

  public WaypointCategory getCategory() {
    return category;
  }

  public void setCategory(WaypointCategory category) {
    this.category = category;
  }

  public WaypointStatus getStatus() {
    return status;
  }

  public void setStatus(WaypointStatus status) {
    this.status = status;
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

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
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

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
