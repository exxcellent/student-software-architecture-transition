package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.waypoint.WaypointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface WaypointRepository extends JpaRepository<WaypointEntity, Long> {

  List<WaypointEntity> findAllByDate(LocalDate date);

  List<WaypointEntity> findAllByDateAndInspectorIdIn(LocalDate date, Set<Long> inspectorIds);

  List<WaypointEntity> findAllByDateAndInspectorId(LocalDate date, Long inspectorId);

  List<WaypointEntity> findAllByInspectorIdIn(Set<Long> inspectorIds);

  WaypointEntity findByDateAndInspectorIdAndId(LocalDate date, Long inspectorId, Long waypointId);

  WaypointEntity findByDateAndInspectorIdAndOrderIndex(LocalDate date, Long inspectorId, Integer orderIndex);
}
