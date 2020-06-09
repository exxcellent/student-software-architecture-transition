package de.exxcellent.student.softwarearchitecture.transition.component.user.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.user.data.entities.UserEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.types.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataAccessMapper {
  UserEntity toEntity(UserDTO dto);
  UserDTO toDTO(UserEntity entity);
}