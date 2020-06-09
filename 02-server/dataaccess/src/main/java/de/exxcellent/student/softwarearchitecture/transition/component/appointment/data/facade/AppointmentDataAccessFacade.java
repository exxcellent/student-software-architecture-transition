package de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.AppointmentRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.data.mapper.AppointmentDataAccessMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.AppointmentDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.appointment.dataaccess.types.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AppointmentDataAccessFacade implements AppointmentDataAccess {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentDataAccessFacade(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        var entities = appointmentRepository.findAll();
        return entities.stream().map(AppointmentDataAccessMapper.toAppointmentDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(Long id) {
        var entity = appointmentRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND_ERROR,
                String.format("Entity with id '%s' not found.", id), id));
        return AppointmentDataAccessMapper.toAppointmentDTO.apply(entity);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO newDTO, User user) {
        var entity = AppointmentDataAccessMapper.toAppointmentEntity.apply(newDTO);

        return null;
    }

    @Override
    public AppointmentDTO update(AppointmentDTO updatedDTO, User user) {
        return null;
    }

    @Override
    public void deleteById(Long processId) {

    }

    @Override
    public AppointmentDTO saveAndFlush(AppointmentDTO entity) {
        return null;
    }
}
