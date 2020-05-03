package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.converter;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.WaypointCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class WaypointCategoryConverter implements Converter<String, WaypointCategory> {

  private static final String DEFAULT_CATEGORY = WaypointCategory.ALL.name();

  @Override
  public WaypointCategory convert(String source) {
    return WaypointCategory.valueOf(StringUtils.defaultIfBlank(source, DEFAULT_CATEGORY).toUpperCase());
  }

}
