package de.exxcellent.student.softwarearchitecture.transition.component.user.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.user.data.entities.UserPermissionEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.types.UserPermissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPermissionDataAccessMapper {
  UserPermissionEntity toEntity(UserPermissionDTO dto);
  UserPermissionDTO toDTO(UserPermissionEntity entity);
}