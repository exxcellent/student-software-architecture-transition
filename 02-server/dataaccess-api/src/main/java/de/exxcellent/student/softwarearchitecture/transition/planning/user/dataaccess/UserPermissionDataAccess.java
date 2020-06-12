package de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserPermissionDTO;

import java.util.List;

public interface UserPermissionDataAccess {

    List<UserPermissionDTO> findAllByUserId(Long userId);

}
