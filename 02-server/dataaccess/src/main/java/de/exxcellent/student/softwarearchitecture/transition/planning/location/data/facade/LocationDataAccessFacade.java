package de.exxcellent.student.softwarearchitecture.transition.planning.location.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.data.entities.LocationEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.data.mapper.LocationDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.data.repository.LocationRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.dataaccess.LocationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.dataaccess.types.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationDataAccessFacade extends CrudDataAccessFacade<LocationEntity> implements LocationDataAccess {

    private final LocationDataAccessMapperImpl mapper;

    @Autowired
    public LocationDataAccessFacade(LocationRepository locationRepository,
                                    DateTimeUtil dateTimeUtil,
                                    LocationDataAccessMapperImpl mapper) {
        super(locationRepository, dateTimeUtil);
        this.mapper = mapper;
    }

    @Override
    public List<LocationDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public LocationDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public LocationDTO create(LocationDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public LocationDTO update(LocationDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }
}
