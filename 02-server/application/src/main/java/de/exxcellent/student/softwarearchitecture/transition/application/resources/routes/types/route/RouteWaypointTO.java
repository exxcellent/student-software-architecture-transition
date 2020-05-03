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

  private String label;
  private String address;

  private Float latitude;
  private Float longitude;

  private WaypointCategory category;

  private String arrivalStartedAt;
  private String finishedAt;

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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
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
}
