package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.datetime;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class DateTimeUtil {

  public OffsetDateTime now() {
    return OffsetDateTime.now();
  }
}
