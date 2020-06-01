package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.validation.ResponseCondition;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.mapper.RouteMapper;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route.RouteWaypointTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route.WaypointUpdateCTO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.NotificationComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.RouteComponent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Navigation")
@SecurityScheme(name = "jwtAuth",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer")
@RestController
@RequestMapping("v1/waypoints")
public class WaypointsResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(WaypointsResourceV1.class);

  private final RouteComponent routeComponent;
  private final NotificationComponent notificationComponent;

  @Autowired
  public WaypointsResourceV1(RouteComponent routeComponent, NotificationComponent notificationComponent) {
    this.routeComponent = routeComponent;
    this.notificationComponent = notificationComponent;
  }

  @Operation(summary = "Find Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{wayPointId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO findWayPointById(@PathVariable("wayPointId") Long wayPointId) {

    var waypoint = routeComponent.findWaypoint(wayPointId);

    ResponseCondition.checkNotNull(waypoint, "No waypoint found with id = '%s'",
        wayPointId);

    return RouteMapper.toRouteWaypointTO.apply(waypoint);
  }

  @Operation(summary = "Update Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{wayPointId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO updateWayPoint(@PathVariable("wayPointId") Long wayPointId,
                                        @RequestBody() RouteWaypointTO routeWaypointTO) {
    var waypointDO = RouteMapper.toWaypointDO.apply(routeWaypointTO);

    var updatedWaypoint = routeComponent.updateWaypoint(wayPointId, waypointDO, CurrentUser.getUser());

    return RouteMapper.toRouteWaypointTO.apply(updatedWaypoint);
  }

  @Operation(summary = "Finish Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{wayPointId}/finish",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public WaypointUpdateCTO finishWaypoint(@PathVariable("wayPointId") Long wayPointId,
                                          @RequestBody() RouteWaypointTO routeWaypointTO) {
    var waypointDO = RouteMapper.toWaypointDO.apply(routeWaypointTO);

    var updatedWaypoint = routeComponent.finishWaypoint(wayPointId, waypointDO, CurrentUser.getUser());

    return RouteMapper.toWaypointUpdateCTO.apply(updatedWaypoint);
  }


  @Operation(summary = "Cancel Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{wayPointId}/cancel",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public WaypointUpdateCTO cancelWaypoint(@PathVariable("wayPointId") Long wayPointId,
                                        @RequestBody() RouteWaypointTO routeWaypointTO) {
    var waypointDO = RouteMapper.toWaypointDO.apply(routeWaypointTO);

    var updatedWaypoint = routeComponent.cancelWaypoint(wayPointId, waypointDO, CurrentUser.getUser());

    return RouteMapper.toWaypointUpdateCTO.apply(updatedWaypoint);
  }



}
