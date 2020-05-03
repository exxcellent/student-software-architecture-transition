package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.converter;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationChannelConverter implements Converter<String, NotificationChannel> {

  private static final String DEFAULT_CHANNEL = NotificationChannel.AUTOMATIC.name();

  @Override
  public NotificationChannel convert(String source) {
    return NotificationChannel.valueOf(StringUtils.defaultIfBlank(source, DEFAULT_CHANNEL).toUpperCase());
  }

}
