package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

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
public class RoutesCTO implements ReadOnlyTO {

  private List<RouteCTO> routes;

  public RoutesCTO() {
  }

  public List<RouteCTO> getRoutes() {
    return routes;
  }

  public void setRoutes(List<RouteCTO> routes) {
    this.routes = routes;
  }
}
