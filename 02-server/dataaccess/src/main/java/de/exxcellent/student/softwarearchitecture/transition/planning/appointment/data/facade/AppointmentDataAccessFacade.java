package de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.mapper.AppointmentDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.data.repository.AppointmentRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.dataaccess.AppointmentDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.appointment.dataaccess.types.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentDataAccessFacade extends CrudDataAccessFacade<AppointmentEntity> implements AppointmentDataAccess {

    private final AppointmentDataAccessMapperImpl mapper;

    @Autowired
    public AppointmentDataAccessFacade(AppointmentRepository appointmentRepository,
                                       DateTimeUtil dateTimeUtil,
                                       AppointmentDataAccessMapperImpl mapper) {
        super(appointmentRepository, dateTimeUtil);
        this.mapper = mapper;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
