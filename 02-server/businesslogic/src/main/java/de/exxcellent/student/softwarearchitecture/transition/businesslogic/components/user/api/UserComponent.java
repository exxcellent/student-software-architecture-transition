package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.common.TechnicalComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.UserDO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface UserComponent extends TechnicalComponent {

  UserDO findByName(String userName);

  boolean hasPermission(Long userId, Permission permission);
}
