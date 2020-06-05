package de.exxcellent.student.softwarearchitecture.transition.component.location.api;

import de.exxcellent.student.softwarearchitecture.transition.component.location.api.types.LocationDO;
import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;

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

  void delete(Long id);
}
