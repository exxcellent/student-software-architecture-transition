package de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.RouteCalculation;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class RouteCalculationConverter implements Converter<String, RouteCalculation> {

  private static final String DEFAULT_MODE = RouteCalculation.NORMAL.name();

  @Override
  public RouteCalculation convert(String source) {
      return RouteCalculation.valueOf(StringUtils.defaultIfBlank(source, DEFAULT_MODE).toUpperCase());
  }

}
