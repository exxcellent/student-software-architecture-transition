package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

import java.util.Map;

/**
 * JSON example:
 *
 * <pre>
 * {
 *   "dates": {
 *     "2020-01-01: {
 *       "routes": [
 *          { ... }
 *       ]
 *     },
 *     "2020-01-02: {
 *       "routes": [
 *          { ... }
 *       ]
 *     }
 *   }
 * }
 * </pre>
 */
public class RouteOverviewCTO {

  private Map<String, RouteTO> dates;

  public RouteOverviewCTO() {
  }

  public Map<String, RouteTO> getDates() {
    return dates;
  }

  public void setDates(Map<String, RouteTO> dates) {
    this.dates = dates;
  }
}
