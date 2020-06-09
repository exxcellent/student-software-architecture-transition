package de.exxcellent.student.softwarearchitecture.transition.component.user.logic;

import de.exxcellent.student.softwarearchitecture.transition.component.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.component.user.data.UserPermissionRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.user.data.UserRepository;
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

  private final UserPermissionRepository userPermissionRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserLogic(UserPermissionRepository userPermissionRepository, UserRepository userRepository) {
    this.userPermissionRepository = userPermissionRepository;
    this.userRepository = userRepository;
  }

  public boolean hasPermission(Long userId, Permission permission) {
    List<Permission> permissions = getPermissionList(userId);

    return permissions.contains(permission);
  }

  public List<Permission> getPermissionList(Long userId) {
    var userPermissions = userPermissionRepository.findAllByUserId(userId);
    return UserMapper.toPermissionList.apply(userPermissions);
  }

  public UserDTO findByName(String userName) {
    return userRepository.findByName(userName);
  }
}

