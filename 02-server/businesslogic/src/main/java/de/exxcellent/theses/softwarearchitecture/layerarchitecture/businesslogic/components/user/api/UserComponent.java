package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.common.TechnicalComponent;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.Permission;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.UserDO;

import java.util.Set;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface UserComponent extends TechnicalComponent {

  UserDO findByName(String userName);

  boolean hasPermission(Long userId, Permission permission);
}
