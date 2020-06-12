package de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.types.UserPermissionDTO;

import java.util.List;

public interface UserPermissionDataAccess {

    List<UserPermissionDTO> findAllByUserId(Long userId);

}
