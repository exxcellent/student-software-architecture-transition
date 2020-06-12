package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.entities.UserEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataAccessMapper {
  UserEntity toEntity(UserDTO dto);
  UserDTO toDTO(UserEntity entity);
}