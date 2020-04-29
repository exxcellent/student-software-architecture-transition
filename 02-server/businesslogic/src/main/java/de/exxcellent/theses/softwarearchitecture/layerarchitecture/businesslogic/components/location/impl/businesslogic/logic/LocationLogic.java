package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.businesslogic.logic;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.User;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.ErrorCode;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.exception.BusinessException;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.resilience.Retry;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.LocationRepository;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.entities.LocationEntity;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class LocationLogic extends CrudLogic<LocationEntity> {

  @Autowired
  public LocationLogic(LocationRepository locationRepository, DateTimeUtil dateTimeUtil) {
    super(locationRepository, dateTimeUtil);
  }
}
