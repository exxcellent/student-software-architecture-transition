package de.exxcellent.student.softwarearchitecture.transition.route.data;

import de.exxcellent.student.softwarearchitecture.transition.route.data.entities.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

  @Query(value = "SELECT n FROM NotificationEntity n "
      + "WHERE n.waypoint.date = :date AND n.waypoint.inspectorId = :inspectorId")
  List<NotificationEntity> findAllByDateAndInspectorId(@Param("date") LocalDate date,
                                                       @Param("inspectorId") Long inspectorId);

  List<NotificationEntity> findAllByWaypointId(Long waypointId);
}