package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.UserComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.impl.businesslogic.logic.UserLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.impl.businesslogic.mapper.UserMapper;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class UserFacade implements UserComponent {

  private final UserLogic userLogic;

  @Autowired
  public UserFacade(UserLogic userLogic) {
    this.userLogic = userLogic;
  }

  @Override
  public UserDO findByName(String userName) {
    Preconditions.checkNotNull(userName, "Username must not be null");

    var userDO = UserMapper.toUserDO.apply(userLogic.findByName(userName));
    var permissions = userLogic.getPermissionList(userDO.getUserId());
    userDO.setPermissions(permissions);

    return userDO;
  }

  @Override
  public boolean hasPermission(Long userId, Permission permission) {
    Preconditions.checkNotNull(userId, "UserId must not be null");
    Preconditions.checkNotNull(permission, "Permission must not be null");
    Preconditions.checkArgument(userId > 0, "UserId must not be positive");

    return userLogic.hasPermission(userId, permission);
  }

}
