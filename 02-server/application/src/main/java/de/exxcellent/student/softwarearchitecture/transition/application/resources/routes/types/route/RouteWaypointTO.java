package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.WaypointCategory;

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
  private Integer orderIndex;

  private String address;

  private Float latitude;
  private Float longitude;

  private WaypointCategory category;

  private String arrivalStartedAt;
  private String finishedAt;

  private Long inspectorId;
  private RouteWaypointContactTO contact;

  public RouteWaypointTO() {
  }

  public Long getWaypointId() {
    return waypointId;
  }

  public void setWaypointId(Long waypointId) {
    this.waypointId = waypointId;
  }

  public Integer getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(Integer orderIndex) {
    this.orderIndex = orderIndex;
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

  public WaypointCategory getCategory() {
    return category;
  }

  public void setCategory(WaypointCategory category) {
    this.category = category;
  }

  public String getArrivalStartedAt() {
    return arrivalStartedAt;
  }

  public void setArrivalStartedAt(String arrivalStartedAt) {
    this.arrivalStartedAt = arrivalStartedAt;
  }

  public String getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(String finishedAt) {
    this.finishedAt = finishedAt;
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public RouteWaypointContactTO getContact() {
    return contact;
  }

  public void setContact(RouteWaypointContactTO contact) {
    this.contact = contact;
  }
}
