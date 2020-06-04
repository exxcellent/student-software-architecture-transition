package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.converters;

import de.exxcellent.student.softwarearchitecture.transition.planning.routes.resources.converter.NotificationChannelConverter;
import de.exxcellent.student.softwarearchitecture.transition.planning.routes.resources.converter.RouteCalculationConverter;
import de.exxcellent.student.softwarearchitecture.transition.planning.routes.resources.converter.WaypointCategoryConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Configuration
public class WebMvcConverterConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
      registry.addConverter(new RouteCalculationConverter());
      registry.addConverter(new WaypointCategoryConverter());
      registry.addConverter(new NotificationChannelConverter());
    }

}
