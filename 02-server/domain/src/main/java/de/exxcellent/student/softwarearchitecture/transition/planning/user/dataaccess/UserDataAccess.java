package de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserDTO;

public interface UserDataAccess extends CrudDataAccess<UserDTO> {

    UserDTO findByName(String userName);
}
