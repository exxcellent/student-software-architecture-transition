package de.exxcellent.student.softwarearchitecture.transition.planning.routes.resources.types.route;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.BusinessTO;

public class WaypointUpdateCTO extends BusinessTO {

  private RouteWaypointTO updatedWaypoint;
  private RouteWaypointTO nextWaypoint;

  public WaypointUpdateCTO() {
  }

  public RouteWaypointTO getUpdatedWaypoint() {
    return updatedWaypoint;
  }

  public void setUpdatedWaypoint(RouteWaypointTO updatedWaypoint) {
    this.updatedWaypoint = updatedWaypoint;
  }

  public RouteWaypointTO getNextWaypoint() {
    return nextWaypoint;
  }

  public void setNextWaypoint(RouteWaypointTO nextWaypoint) {
    this.nextWaypoint = nextWaypoint;
  }
}
