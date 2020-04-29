package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.businesslogic.mapper;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.ErrorCode;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.exception.BusinessException;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.api.types.LocationDO;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.entities.LocationEntity;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.Permission;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.UserDO;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities.PermissionName;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities.UserEntity;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities.UserPermissionEntity;

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

    userDO.setUserId(entity.getUserId());
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
