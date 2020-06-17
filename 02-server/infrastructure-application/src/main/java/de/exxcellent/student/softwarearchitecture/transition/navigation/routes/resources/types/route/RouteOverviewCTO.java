package de.exxcellent.student.softwarearchitecture.transition.navigation.routes.resources.types.route;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.ReadOnlyTO;

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
public class RouteOverviewCTO implements ReadOnlyTO {

  private Map<String, RouteCTO> dates;

  public RouteOverviewCTO() {
  }

  public Map<String, RouteCTO> getDates() {
    return dates;
  }

  public void setDates(Map<String, RouteCTO> dates) {
    this.dates = dates;
  }
}
