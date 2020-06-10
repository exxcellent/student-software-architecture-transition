package de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.entities.InspectorEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.mapper.InspectorDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.repository.InspectorRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.InspectorDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.types.InspectorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InspectorDataAccessFacade extends CrudDataAccessFacade<InspectorEntity> implements InspectorDataAccess {

    private final InspectorDataAccessMapperImpl mapper;

    @Autowired
    public InspectorDataAccessFacade(InspectorRepository inspectorRepository,
                                     DateTimeUtil dateTimeUtil,
                                     InspectorDataAccessMapperImpl mapper) {
        super(inspectorRepository, dateTimeUtil);
        this.mapper = mapper;
    }

    @Override
    public List<InspectorDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public InspectorDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public InspectorDTO create(InspectorDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public InspectorDTO update(InspectorDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
