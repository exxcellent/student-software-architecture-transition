package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

/**
 * JSON example:
 *
 * <pre>
 * {
 *   "waypointId": 1,
 *   ...
 * }
 * </pre>
 */
public class RouteWaypointTO extends BusinessTO {

  private Long waypointId;
  private String date;
  private Integer orderIndex;
  private RouteWaypointCategory category;
  private RouteWaypointStatus status;

  private Long inspectorId;
  private Long appointmentId;

  private RouteWaypointContactTO contact;
  private RouteWaypointLocationTO location;

  public RouteWaypointTO() {
  }

  public Long getWaypointId() {
    return waypointId;
  }

  public void setWaypointId(Long waypointId) {
    this.waypointId = waypointId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(Integer orderIndex) {
    this.orderIndex = orderIndex;
  }

  public RouteWaypointCategory getCategory() {
    return category;
  }

  public void setCategory(RouteWaypointCategory category) {
    this.category = category;
  }

  public RouteWaypointStatus getStatus() {
    return status;
  }

  public void setStatus(RouteWaypointStatus status) {
    this.status = status;
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

  public RouteWaypointContactTO getContact() {
    return contact;
  }

  public void setContact(RouteWaypointContactTO contact) {
    this.contact = contact;
  }

  public RouteWaypointLocationTO getLocation() {
    return location;
  }

  public void setLocation(RouteWaypointLocationTO location) {
    this.location = location;
  }
}
