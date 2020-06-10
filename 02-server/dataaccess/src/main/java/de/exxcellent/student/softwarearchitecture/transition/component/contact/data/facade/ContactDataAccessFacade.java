package de.exxcellent.student.softwarearchitecture.transition.component.contact.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.data.entities.ContactEntity;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.data.mapper.ContactDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.data.repository.ContactRepository;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.dataaccess.ContactDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.dataaccess.types.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactDataAccessFacade extends CrudDataAccessFacade<ContactEntity> implements ContactDataAccess {

    private final ContactDataAccessMapperImpl mapper;

    @Autowired
    public ContactDataAccessFacade(ContactRepository contactRepository,
                                   DateTimeUtil dateTimeUtil,
                                   ContactDataAccessMapperImpl mapper) {
        super(contactRepository, dateTimeUtil);
        this.mapper = mapper;
    }

    @Override
    public List<ContactDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ContactDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public ContactDTO create(ContactDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public ContactDTO update(ContactDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
