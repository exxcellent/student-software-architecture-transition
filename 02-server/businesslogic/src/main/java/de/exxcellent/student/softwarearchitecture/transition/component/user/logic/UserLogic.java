package de.exxcellent.student.softwarearchitecture.transition.component.user.logic;

import de.exxcellent.student.softwarearchitecture.transition.component.user.mapper.UserMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.component.user.data.UserPermissionRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.user.data.UserRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.user.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

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

  public boolean hasAnyPermission(Long userId, Set<Permission> permissions) {
    List<Permission> permissionList = getPermissionList(userId);

    return permissionList.contains(permissions);
  }

  public boolean hasAllPermission(Long userId, Set<Permission> permissions) {
    List<Permission> permissionList = getPermissionList(userId);

    return permissionList.containsAll(permissions);
  }

  public List<Permission> getPermissionList(Long userId) {
    var userPermissions = userPermissionRepository.findAllByUserId(userId);
    return UserMapper.toPermissionList.apply(userPermissions);
  }

  public UserEntity findByName(String userName) {
    return userRepository.findByName(userName);
  }
}

