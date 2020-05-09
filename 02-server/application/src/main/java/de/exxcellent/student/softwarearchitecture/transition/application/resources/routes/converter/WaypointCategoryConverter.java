package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.converter;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route.RouteWaypointCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class WaypointCategoryConverter implements Converter<String, RouteWaypointCategory> {

  private static final String DEFAULT_CATEGORY = RouteWaypointCategory.ALL.name();

  @Override
  public RouteWaypointCategory convert(String source) {
    return RouteWaypointCategory.valueOf(StringUtils.defaultIfBlank(source, DEFAULT_CATEGORY).toUpperCase());
  }

}
