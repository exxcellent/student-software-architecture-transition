package de.exxcellent.student.softwarearchitecture.transition.component.process.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.component.process.data.entities.ProcessEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.process.data.mapper.ProcessDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.component.process.data.repository.ProcessRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.process.dataaccess.ProcessDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.process.dataaccess.types.ProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessDataAccessFacade extends CrudDataAccessFacade<ProcessEntity> implements ProcessDataAccess {

    private final ProcessDataAccessMapperImpl mapper;

    @Autowired
    public ProcessDataAccessFacade(ProcessRepository processRepository,
                                   DateTimeUtil dateTimeUtil,
                                   ProcessDataAccessMapperImpl mapper) {
        super(processRepository, dateTimeUtil);
        this.mapper = mapper;
    }

    @Override
    public List<ProcessDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProcessDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public ProcessDTO create(ProcessDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public ProcessDTO update(ProcessDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
