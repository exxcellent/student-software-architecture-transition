package de.exxcellent.student.softwarearchitecture.transition.navigation.route.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.common.types.Pair;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.WaypointDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.Status;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.WaypointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class RouteLogic extends CrudLogic<WaypointDTO> {
  private final WaypointDataAccess waypointDataAccess;

  @Autowired
  public RouteLogic(WaypointDataAccess waypointDataAccess) {
    super(waypointDataAccess);
    this.waypointDataAccess = waypointDataAccess;
  }

   public List<WaypointDTO> findAllByInspectors(Set<Long> filterByInspectorIds) {
    if (filterByInspectorIds == null || filterByInspectorIds.isEmpty()) {
      return waypointDataAccess.findAll();
    } else {
      return waypointDataAccess.findAllByInspectorIdIn(filterByInspectorIds);
    }
   }

   public List<WaypointDTO> findAllByDate(LocalDate date, Set<Long> filterByInspectorIds) {
    if (filterByInspectorIds == null || filterByInspectorIds.isEmpty()) {
      return waypointDataAccess.findAllByDate(date);
    } else {
      return waypointDataAccess.findAllByDateAndInspectorIdIn(date, filterByInspectorIds);
    }
   }

   public List<WaypointDTO> findAllByDateAndInspector(LocalDate date, Long inspectorId) {
      return waypointDataAccess.findAllByDateAndInspectorId(date, inspectorId);
   }

  public WaypointDTO findByDateAndInspectorAndWaypointId(LocalDate date, Long inspectorId, Long wayPointId) {
    return waypointDataAccess.findByDateAndInspectorIdAndId(date, inspectorId, wayPointId);

  }

  public Pair<WaypointDTO, WaypointDTO> finishWaypoint(WaypointDTO waypoint, User user) {
    var waypointDTO = this.findById(waypoint.getId());
    var currentOrderIndex = waypointDTO.getOrderIndex();
    var nextOrderIndex = currentOrderIndex + 1;

    // check current state
    Preconditions.checkArgument(waypointDTO.getStatus() == Status.ACTIVE, "Current Waypoint state must be ACTIVE");
    Preconditions.checkArgument(waypoint.getStatus() == Status.FINISHED, "Target Waypoint state must be FINISHED");

    // finish current waypoint
    var updatedWaypoint = this.update(waypoint, user);
    WaypointDTO next = null;

    // activate next waypoint
    boolean nextWaypointUpdated = false;
    while(!nextWaypointUpdated) {
      var nextWaypoint = this.waypointDataAccess.findByDateAndInspectorIdAndOrderIndex(waypointDTO.getDate(),
          waypointDTO.getInspectorId(), nextOrderIndex);

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


  public Pair<WaypointDTO, WaypointDTO> cancelWaypoint(WaypointDTO waypoint, User user) {
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
      WaypointDTO next = null;

      // activate next waypoint
      if (wasActive) {
        boolean nextWaypointUpdated = false;
        while (!nextWaypointUpdated) {
          var nextWaypoint = this.waypointDataAccess.findByDateAndInspectorIdAndOrderIndex(waypointEntity.getDate(),
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
