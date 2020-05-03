package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import java.util.List;

/**
 * JSON example:
 *
 * <pre>
 * {
 *   "routes": [
 *     { ... }
 *   ]
 * }
 * </pre>
 */
public class RoutesCTO {

  private List<RouteTO> routes;

  public RoutesCTO() {
  }

  public List<RouteTO> getRoutes() {
    return routes;
  }

  public void setRoutes(List<RouteTO> routes) {
    this.routes = routes;
  }
}
