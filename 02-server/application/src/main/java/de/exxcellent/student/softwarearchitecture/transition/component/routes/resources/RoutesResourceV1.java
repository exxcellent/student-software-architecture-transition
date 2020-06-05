package de.exxcellent.student.softwarearchitecture.transition.component.routes.resources;

import de.exxcellent.student.softwarearchitecture.transition.component.user.resources.CurrentUser;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.validation.ResponseCondition;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.mapper.NotificationMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.mapper.RouteMapper;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.notification.NotificationTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.notification.NotificationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.RouteCTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.RouteCalculation;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.RouteWaypointTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routes.resources.types.route.RoutesCTO;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.NotificationComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.route.api.RouteComponent;
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

import java.util.List;

@Tag(name = "Navigation")
@SecurityScheme(name = "jwtAuth",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer")
@RestController
@RequestMapping("v1/routes")
public class RoutesResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(RoutesResourceV1.class);

  private final RouteComponent routeComponent;
  private final NotificationComponent notificationComponent;

  @Autowired
  public RoutesResourceV1(RouteComponent routeComponent, NotificationComponent notificationComponent) {
    this.routeComponent = routeComponent;
    this.notificationComponent = notificationComponent;
  }

  @Operation(summary = "Find All Routes")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RoutesCTO findByDate(@RequestParam(name = "inspectors", required = false) List<String> showInspectors) {

    var inspectorIds = RouteMapper.toInspectorIdList.apply(showInspectors);
    var routes = routeComponent.findAll(inspectorIds);

    return RouteMapper.toRoutesCTO.apply(routes);
  }

  @Operation(summary = "Find All Routes of a Day")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RoutesCTO findByDate(@PathVariable("date") String dateOfRoutes,
                            @RequestParam(name = "inspectors", required = false) List<String> showInspectors) {

    var date = RouteMapper.toLocalDate.apply(dateOfRoutes);
    var inspectorIds = RouteMapper.toInspectorIdList.apply(showInspectors);
    var routes = routeComponent.findAllByDate(date, inspectorIds);

    return RouteMapper.toRoutesCTO.apply(routes);
  }

  @Operation(summary = "Find Route of a Day and Inspector")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteCTO findByInspectorId(@PathVariable("date") String dateOfRoutes,
                                    @PathVariable("inspectorId") Long inspectorId,
                                    @RequestParam(name = "mode", required = false, defaultValue = "RANDOM") RouteCalculation routeCalculation) {

    var date = RouteMapper.toLocalDate.apply(dateOfRoutes);
    var mode = RouteMapper.toRouteCalculationMode.apply(routeCalculation);
    var route = routeComponent.findByDateAndInspector(date, inspectorId, mode);

    ResponseCondition.checkNotNull(route, "No route found with date = '%s' and inspector = '%s'",
        dateOfRoutes, inspectorId);

    return RouteMapper.toRouteCTO.apply(route);
  }

  @Operation(summary = "Find Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/waypoints/{wayPointId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO findWayPointById(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId,
                                          @PathVariable("wayPointId") Long wayPointId) {

    var date = RouteMapper.toLocalDate.apply(dateOfRoutes);
    var waypoint = routeComponent.findWaypoint(date, inspectorId, wayPointId);

    ResponseCondition.checkNotNull(waypoint, "No waypoint found with date = '%s' and inspector = '%s'",
        dateOfRoutes, inspectorId);

    return RouteMapper.toRouteWaypointTO.apply(waypoint);
  }

  @Operation(summary = "Update Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{date}/inspectors/{inspectorId}/waypoints/{wayPointId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO updateWayPoint(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId,
                                          @PathVariable("wayPointId") Long wayPointId,
                                          @RequestBody() RouteWaypointTO routeWaypointTO) {

    var waypointDO = RouteMapper.toWaypointDO.apply(routeWaypointTO);

    var updatedWaypoint = routeComponent.updateWaypoint(wayPointId, waypointDO, CurrentUser.getUser());

    return RouteMapper.toRouteWaypointTO.apply(updatedWaypoint);
  }

  @Operation(summary = "Find All Notifications of a Route")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/notifications",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NotificationsCTO findNotificationsByInspectorId(@PathVariable("date") String dateOfRoutes,
                                                         @PathVariable("inspectorId") Long inspectorId) {

    var date = RouteMapper.toLocalDate.apply(dateOfRoutes);

    var notifications = notificationComponent.findAllNotificationsByDateAndInspector(date, inspectorId);

    return NotificationMapper.toNotificationsCTO.apply(notifications);
  }

  @Operation(summary = "Find All Notifications of a Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/waypoints/{waypointId}/notifications",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NotificationsCTO findNotificationsByInspectorId(@PathVariable("date") String dateOfRoutes,
                                                         @PathVariable("inspectorId") Long inspectorId,
                                                         @PathVariable("waypointId") Long wayPointId) {

    var notifications = notificationComponent.findAllNotificationsByWaypointId(wayPointId);

    return NotificationMapper.toNotificationsCTO.apply(notifications);
  }

  @Operation(summary = "Create a new Notification for a Waypoint")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.POST,
      path = "{date}/inspectors/{inspectorId}/waypoints/{waypointId}/notifications",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NotificationTO createNotification(@PathVariable("date") String dateOfRoutes,
                                           @PathVariable("inspectorId") Long inspectorId,
                                           @PathVariable("waypointId") Long wayPointId,
                                           @RequestBody() NotificationTO notificationTO) {

    var notificationDO = NotificationMapper.toNotificationDO.apply(notificationTO);

    var notification = notificationComponent.addNotificationToRoute(wayPointId, notificationDO,
        CurrentUser.getUser());

    return NotificationMapper.toNotificationTO.apply(notification);
  }

}
