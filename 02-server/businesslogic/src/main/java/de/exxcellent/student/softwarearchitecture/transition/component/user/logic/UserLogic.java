package de.exxcellent.student.softwarearchitecture.transition.component.user.logic;

import de.exxcellent.student.softwarearchitecture.transition.component.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.UserDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.UserPermissionDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.types.UserDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
@Transactional(readOnly = true)
public class UserLogic {

  private final UserPermissionDataAccess userPermissionDataAccess;
  private final UserDataAccess userDataAccess;

  @Autowired
  public UserLogic(UserPermissionDataAccess userPermissionDataAccess, UserDataAccess userDataAccess) {
    this.userPermissionDataAccess = userPermissionDataAccess;
    this.userDataAccess = userDataAccess;
  }

  public boolean hasPermission(Long userId, Permission permission) {
    List<Permission> permissions = getPermissionList(userId);

    return permissions.contains(permission);
  }

  public List<Permission> getPermissionList(Long userId) {
    var userPermissions = userPermissionDataAccess.findAllByUserId(userId);
    return UserMapper.toPermissionList.apply(userPermissions);
  }

  public UserDTO findByName(String userName) {
    return userDataAccess.findByName(userName);
  }
}

