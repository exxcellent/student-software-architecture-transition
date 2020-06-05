package de.exxcellent.student.softwarearchitecture.transition.user.mapper;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.user.api.types.UserDO;
import de.exxcellent.student.softwarearchitecture.transition.user.data.entities.PermissionName;
import de.exxcellent.student.softwarearchitecture.transition.user.data.entities.UserEntity;
import de.exxcellent.student.softwarearchitecture.transition.user.data.entities.UserPermissionEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class UserMapper {

  private UserMapper() {
  }

  public static final Function<UserEntity, UserDO> toUserDO = entity -> {
    var userDO = new UserDO();

    userDO.setUserId(entity.getId());
    userDO.setName(entity.getName());
    userDO.setPassword(entity.getPassword());

    return userDO;
  };

  public static final Function<List<UserPermissionEntity>, List<Permission>> toPermissionList =
      userPermissionEntities -> userPermissionEntities.stream()
        .map(UserMapper.toPermission)
        .collect(Collectors.toList());

  public static final Function<UserPermissionEntity, Permission> toPermission = entity ->
      UserMapper.mapPermission.apply(entity.getPermission());

  private static final Function<PermissionName, Permission> mapPermission = permissionName -> {
    switch (permissionName) {
      case READ: return Permission.READ_ALL;
      case WRITE: return Permission.WRITE_ALL;
      case DELETE: return Permission.DELETE_ALL;
      default: throw new BusinessException(ErrorCode.NO_MATCH_FOUND,
          String.format("No match found for permission '%s'", permissionName), permissionName);
    }
  };

}
