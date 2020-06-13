package de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.entities.notification.NotificationEntity;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.notification.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDataAccessMapper {
  NotificationEntity toEntity(NotificationDTO dto);
  NotificationDTO toDTO(NotificationEntity entity);
}