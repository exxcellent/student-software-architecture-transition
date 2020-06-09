package de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.AppointmentRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.entities.AppointmentEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.mapper.AppointmentDataAccessMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.AppointmentDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentDataAccessFacade extends CrudDataAccessFacade<AppointmentEntity> implements AppointmentDataAccess {

    @Autowired
    public AppointmentDataAccessFacade(AppointmentRepository appointmentRepository, DateTimeUtil dateTimeUtil) {
       super(appointmentRepository, dateTimeUtil);
    }

    @Override
    public List<AppointmentDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(AppointmentDataAccessMapper.toAppointmentDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(Long id) {
        var entity = findEntityById(id);
        return AppointmentDataAccessMapper.toAppointmentDTO.apply(entity);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO newDTO, User user) {
        var entity = AppointmentDataAccessMapper.toAppointmentEntity.apply(newDTO);
        var createdEntity = createEntity(entity, user);
        return AppointmentDataAccessMapper.toAppointmentDTO.apply(createdEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO updatedDTO, User user) {
        var entity = AppointmentDataAccessMapper.toAppointmentEntity.apply(updatedDTO);
        var updatedEntity = createEntity(entity, user);
        return AppointmentDataAccessMapper.toAppointmentDTO.apply(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
