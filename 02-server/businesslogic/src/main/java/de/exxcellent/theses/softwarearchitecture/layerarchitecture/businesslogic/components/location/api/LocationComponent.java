package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.User;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.common.BusinessComponent;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api.types.LocationDO;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.UserDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationComponent extends BusinessComponent {

  List<LocationDO> findAll();

  LocationDO findById(Long locationId);

  LocationDO create(LocationDO newLocationDO, User user);

  LocationDO update(LocationDO locationDO, User user);

  void delete(LocationDO locationDO);
}
