package de.exxcellent.student.softwarearchitecture.transition.planning.user.mapper;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.types.UserDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.PermissionName;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserDTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserPermissionDTO;

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

  public static final Function<UserDTO, UserDO> toUserDO = entity -> {
    var userDO = new UserDO();

    userDO.setUserId(entity.getId());
    userDO.setName(entity.getName());
    userDO.setPassword(entity.getPassword());

    return userDO;
  };

  public static final Function<List<UserPermissionDTO>, List<Permission>> toPermissionList =
      userPermissionEntities -> userPermissionEntities.stream()
        .map(UserMapper.toPermission)
        .collect(Collectors.toList());

  public static final Function<UserPermissionDTO, Permission> toPermission = entity ->
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
