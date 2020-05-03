package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

import java.util.List;

/**
 * JSON example:
 *
 * <pre>
 * {
 *   "waypoints": [
 *     { ... }
 *   ],
 *   "routeId": ...
 * }
 * </pre>
 */
public class RouteTO extends BusinessTO {

  private Long routeId;
  private Long inspectorId;
  private String date;
  private Long totalDurationInSeconds;
  private Long timeRemainingInSeconds;

  private List<RouteWaypointTO> waypoints;

  public RouteTO() {
  }

  public Long getRouteId() {
    return routeId;
  }

  public void setRouteId(Long routeId) {
    this.routeId = routeId;
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Long getTotalDurationInSeconds() {
    return totalDurationInSeconds;
  }

  public void setTotalDurationInSeconds(Long totalDurationInSeconds) {
    this.totalDurationInSeconds = totalDurationInSeconds;
  }

  public Long getTimeRemainingInSeconds() {
    return timeRemainingInSeconds;
  }

  public void setTimeRemainingInSeconds(Long timeRemainingInSeconds) {
    this.timeRemainingInSeconds = timeRemainingInSeconds;
  }

  public List<RouteWaypointTO> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<RouteWaypointTO> waypoints) {
    this.waypoints = waypoints;
  }
}
