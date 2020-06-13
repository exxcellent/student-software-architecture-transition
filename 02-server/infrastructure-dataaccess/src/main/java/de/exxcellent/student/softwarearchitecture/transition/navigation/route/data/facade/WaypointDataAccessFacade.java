package de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccessFacade;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.entities.waypoint.WaypointEntity;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.mapper.WaypointDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.data.repository.WaypointRepository;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.WaypointDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.WaypointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WaypointDataAccessFacade extends CrudDataAccessFacade<WaypointEntity> implements WaypointDataAccess {

    private final WaypointDataAccessMapperImpl mapper;
    private final WaypointRepository repository;

    @Autowired
    public WaypointDataAccessFacade(WaypointRepository waypointRepository,
                                    DateTimeUtil dateTimeUtil,
                                    WaypointDataAccessMapperImpl mapper) {
        super(waypointRepository, dateTimeUtil);
        this.mapper = mapper;
        this.repository = waypointRepository;
    }

    @Override
    public List<WaypointDTO> findAll() {
        var entities = findAllEntities();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public WaypointDTO findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public WaypointDTO create(WaypointDTO newDTO, User user) {
        var entity = mapper.toEntity(newDTO);
        var createdEntity = createEntity(entity, user);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public WaypointDTO update(WaypointDTO updatedDTO, User user) {
        var entity = mapper.toEntity(updatedDTO);
        var updatedEntity = updateEntity(entity, user);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
       deleteEntity(id);
    }

    @Override
    public List<WaypointDTO> findAllByInspectorIdIn(Set<Long> filterByInspectorIds) {
        var entities = repository.findAllByInspectorIdIn(filterByInspectorIds);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<WaypointDTO> findAllByDate(LocalDate date) {
        var entities = repository.findAllByDate(date);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<WaypointDTO> findAllByDateAndInspectorIdIn(LocalDate date, Set<Long> filterByInspectorIds) {
        var entities = repository.findAllByDateAndInspectorIdIn(date, filterByInspectorIds);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<WaypointDTO> findAllByDateAndInspectorId(LocalDate date, Long inspectorId) {
        var entities = repository.findAllByDateAndInspectorId(date, inspectorId);
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public WaypointDTO findByDateAndInspectorIdAndId(LocalDate date, Long inspectorId, Long wayPointId) {
        var entity = repository.findByDateAndInspectorIdAndId(date, inspectorId, wayPointId);
        return mapper.toDTO(entity);
    }

    @Override
    public WaypointDTO findByDateAndInspectorIdAndOrderIndex(LocalDate date, Long inspectorId, int nextOrderIndex) {
        var entity = repository.findByDateAndInspectorIdAndOrderIndex(date, inspectorId, nextOrderIndex);
        return mapper.toDTO(entity);
    }
}
