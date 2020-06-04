package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.route;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class RouteDO {

  private LocalDate date;
  private Duration totalDurationInSeconds;
  private Duration timeRemainingInSeconds;

  private List<WaypointDO> waypoints;

  public RouteDO() {
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Duration getTotalDurationInSeconds() {
    return totalDurationInSeconds;
  }

  public void setTotalDurationInSeconds(Duration totalDurationInSeconds) {
    this.totalDurationInSeconds = totalDurationInSeconds;
  }

  public Duration getTimeRemainingInSeconds() {
    return timeRemainingInSeconds;
  }

  public void setTimeRemainingInSeconds(Duration timeRemainingInSeconds) {
    this.timeRemainingInSeconds = timeRemainingInSeconds;
  }

  public List<WaypointDO> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<WaypointDO> waypoints) {
    this.waypoints = waypoints;
  }
}
