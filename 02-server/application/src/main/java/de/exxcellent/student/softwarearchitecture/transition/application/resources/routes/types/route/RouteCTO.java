package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

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
public class RouteCTO implements ReadOnlyTO {

  private String date;
  private Long totalDurationInSeconds;
  private Long timeRemainingInSeconds;

  private List<RouteWaypointTO> waypoints;

  public RouteCTO() {
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
