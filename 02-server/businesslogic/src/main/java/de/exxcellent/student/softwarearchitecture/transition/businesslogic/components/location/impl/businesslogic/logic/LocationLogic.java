package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data.LocationRepository;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data.entities.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
@Transactional(readOnly = true)
public class LocationLogic extends CrudLogic<LocationEntity> {

  @Autowired
  public LocationLogic(LocationRepository locationRepository, DateTimeUtil dateTimeUtil) {
    super(locationRepository, dateTimeUtil);
  }
}
