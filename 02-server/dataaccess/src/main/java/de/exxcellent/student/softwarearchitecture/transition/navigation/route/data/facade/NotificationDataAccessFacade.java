package de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.entities.notification.NotificationEntity;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.mapper.NotificationDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.repository.NotificationRepository;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.NotificationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationDataAccessFacade extends CrudDataAccessFacade<NotificationEntity> implements NotificationDataAccess {

    private final NotificationDataAccessMapperImpl mapper;
    private final NotificationRepository repository;

    @Autowired
    public NotificationDataAccessFacade(NotificationRepository notificationRepository,
                                        DateTimeUtil dateTimeUtil,
                                        NotificationDataAccessMapperImpl mapper) {
        super(notificationRepository, dateTimeUtil);
        this.mapper = mapper;
        this.repository = notificationRepository;
    }

    @Override
    public List<NotificationDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public NotificationDTO create(NotificationDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public NotificationDTO update(NotificationDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }

    @Override
    public List<NotificationDTO> findAllByDateAndInspectorId(LocalDate date, Long inspectorId) {
        var entities = repository.findAllByDateAndInspectorId(date, inspectorId);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> findAllByWaypointId(Long waypointId) {
        var entities = repository.findAllByWaypointId(waypointId);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}