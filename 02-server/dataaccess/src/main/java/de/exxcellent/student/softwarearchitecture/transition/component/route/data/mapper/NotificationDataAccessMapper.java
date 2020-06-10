package de.exxcellent.student.softwarearchitecture.transition.component.route.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.route.data.entities.notification.NotificationEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.route.dataaccess.types.notification.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDataAccessMapper {
  NotificationEntity toEntity(NotificationDTO dto);
  NotificationDTO toDTO(NotificationEntity entity);
}