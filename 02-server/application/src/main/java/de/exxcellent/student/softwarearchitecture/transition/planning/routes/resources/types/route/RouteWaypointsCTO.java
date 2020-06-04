package de.exxcellent.student.softwarearchitecture.transition.planning.routes.resources.types.route;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class RouteWaypointsCTO {

  private List<RouteWaypointTO> waypoints;

  public RouteWaypointsCTO() {
  }

  public List<RouteWaypointTO> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<RouteWaypointTO> waypoints) {
    this.waypoints = waypoints;
  }
}
