package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.RouteDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.WaypointRepository;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.WaypointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class RouteLogic extends CrudLogic<WaypointEntity> {
  private final WaypointRepository waypointRepository;

  @Autowired
  public RouteLogic(WaypointRepository waypointRepository, DateTimeUtil dateTimeUtil) {
    super(waypointRepository, dateTimeUtil);
    this.waypointRepository = waypointRepository;
  }

   public List<WaypointEntity> findAllByInspectors(Set<Long> filterByInspectorIds) {
    if (filterByInspectorIds == null || filterByInspectorIds.isEmpty()) {
      return waypointRepository.findAll();
    } else {
      return waypointRepository.findAllByInspectorIdIn(filterByInspectorIds);
    }
   }

   public List<WaypointEntity> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds) {
    if (filterByInspectorIds == null || filterByInspectorIds.isEmpty()) {
      return waypointRepository.findAllByDate(date);
    } else {
      return waypointRepository.findAllByDateAndInspectorIdIn(date, filterByInspectorIds);
    }
   }

   public List<WaypointEntity> findAllByDateAndInspector(LocalDate date, Long inspectorId) {
      return waypointRepository.findAllByDateAndInspectorId(date, inspectorId);
   }

 }
