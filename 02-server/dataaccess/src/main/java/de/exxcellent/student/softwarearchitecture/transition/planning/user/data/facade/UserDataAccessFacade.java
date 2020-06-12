package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.entities.UserEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.mapper.UserDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.repository.UserRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.UserDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataAccessFacade extends CrudDataAccessFacade<UserEntity> implements UserDataAccess {

    private final UserDataAccessMapperImpl mapper;
    private final UserRepository userRepository;

    @Autowired
    public UserDataAccessFacade(UserRepository userRepository,
                                DateTimeUtil dateTimeUtil,
                                UserDataAccessMapperImpl mapper) {
        super(userRepository, dateTimeUtil);
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public UserDTO create(UserDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public UserDTO update(UserDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }

    @Override
    public UserDTO findByName(String userName) {
        var entity = userRepository.findByName(userName);
        return mapper.toDTO(entity);
    }
}
