package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.mapper.UserPermissionDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.repository.UserPermissionRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.UserPermissionDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types.UserPermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPermissionDataAccessFacade implements UserPermissionDataAccess {

    private final UserPermissionDataAccessMapperImpl mapper;
    private final UserPermissionRepository repository;

    @Autowired
    public UserPermissionDataAccessFacade(UserPermissionRepository repository,
                                          UserPermissionDataAccessMapperImpl mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public List<UserPermissionDTO> findAllByUserId(Long userId) {
        var entities = repository.findAllByUserId(userId);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
