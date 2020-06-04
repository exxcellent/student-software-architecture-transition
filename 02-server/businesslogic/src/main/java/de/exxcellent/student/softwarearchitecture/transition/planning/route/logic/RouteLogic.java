package de.exxcellent.student.softwarearchitecture.transition.planning.route.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.common.types.Pair;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.data.WaypointRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.data.entities.waypoint.Status;
import de.exxcellent.student.softwarearchitecture.transition.planning.route.data.entities.waypoint.WaypointEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

  public WaypointEntity findByDateAndInspectorAndWaypointId(LocalDate date, Long inspectorId, Long wayPointId) {
    return waypointRepository.findByDateAndInspectorIdAndId(date, inspectorId, wayPointId);

  }

  public Pair<WaypointEntity, WaypointEntity> finishWaypoint(WaypointEntity waypoint, User user) {
    var waypointEntity = this.findById(waypoint.getId());
    var currentOrderIndex = waypointEntity.getOrderIndex();
    var nextOrderIndex = currentOrderIndex + 1;

    // check current state
    Preconditions.checkArgument(waypointEntity.getStatus() == Status.ACTIVE, "Current Waypoint state must be ACTIVE");
    Preconditions.checkArgument(waypoint.getStatus() == Status.FINISHED, "Target Waypoint state must be FINISHED");

    // finish current waypoint
    var updatedWaypoint = this.update(waypoint, user);
    WaypointEntity next = null;

    // activate next waypoint
    boolean nextWaypointUpdated = false;
    while(!nextWaypointUpdated) {
      var nextWaypoint = this.waypointRepository.findByDateAndInspectorIdAndOrderIndex(waypointEntity.getDate(),
          waypointEntity.getInspectorId(), nextOrderIndex);

      if (nextWaypoint == null) {
        nextWaypointUpdated = true; // all waypoints of the route completed
      } else if (nextWaypoint.getStatus() == Status.PENDING) {

        // set next waypoint state to ACTIVE
        nextWaypoint.setStatus(Status.ACTIVE);
        next = this.update(nextWaypoint, user);

        nextWaypointUpdated = true;
      } else {
        // find next waypoint
        nextOrderIndex++;
      }
    }

    return new Pair<>(updatedWaypoint, next);
  }


  public Pair<WaypointEntity, WaypointEntity> cancelWaypoint(WaypointEntity waypoint, User user) {
      var waypointEntity = this.findById(waypoint.getId());
      var currentOrderIndex = waypointEntity.getOrderIndex();
      var nextOrderIndex = currentOrderIndex + 1;

      // check current state
      boolean wasActive = waypointEntity.getStatus() == Status.ACTIVE;
      boolean wasPending = waypointEntity.getStatus() == Status.PENDING;
      Preconditions.checkArgument(wasActive || wasPending,
          "Current Waypoint state must be ACTIVE or PENDING");
      Preconditions.checkArgument(waypoint.getStatus() == Status.CANCELED, "Target Waypoint state must be CANCELED");

      // finish current waypoint
      var updatedWaypoint = this.update(waypoint, user);
      WaypointEntity next = null;

      // activate next waypoint
      if (wasActive) {
        boolean nextWaypointUpdated = false;
        while (!nextWaypointUpdated) {
          var nextWaypoint = this.waypointRepository.findByDateAndInspectorIdAndOrderIndex(waypointEntity.getDate(),
              waypointEntity.getInspectorId(), nextOrderIndex);

          if (nextWaypoint == null) {
            nextWaypointUpdated = true; // all waypoints of the route completed
          } else if (nextWaypoint.getStatus() == Status.PENDING) {

            // set next waypoint state to ACTIVE
            nextWaypoint.setStatus(Status.ACTIVE);
            next = this.update(nextWaypoint, user);

            nextWaypointUpdated = true;
          } else {
            // find next waypoint
            nextOrderIndex++;
          }
        }
      }

      return new Pair<>(updatedWaypoint, next);
  }
}
