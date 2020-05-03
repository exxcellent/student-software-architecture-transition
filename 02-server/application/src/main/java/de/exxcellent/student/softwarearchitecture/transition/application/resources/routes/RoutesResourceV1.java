package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.notification.NotificationsCTO;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route.*;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.RouteComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/routes")
public class RoutesResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(RoutesResourceV1.class);

  private final RouteComponent routeComponent;

  @Autowired
  public RoutesResourceV1(RouteComponent routeComponent) {
    this.routeComponent = routeComponent;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteOverviewCTO findAll(@RequestParam(name = "mode", required = false) RouteCalculation routeCalculation,
                                  @RequestParam(name = "inspectors", required = false) List<String> showInspectors) {
    return new RouteOverviewCTO();

  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RoutesCTO findByDate(@PathVariable("date") String dateOfRoutes,
                            @RequestParam(name = "mode", required = false) RouteCalculation routeCalculation,
                            @RequestParam(name = "inspectors", required = false) List<String> showInspectors) {
    return new RoutesCTO();

  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteCTO findByInspectorId(@PathVariable("date") String dateOfRoutes,
                                    @PathVariable("inspectorId") Long inspectorId,
                                    @RequestParam(name = "mode", required = false) RouteCalculation routeCalculation) {
    return new RouteCTO();

  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/waypoints",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointsCTO findWayPointById(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId,
                                          @RequestParam(name = "mode", required = false) RouteCalculation routeCalculation) {
    return new RouteWaypointsCTO();
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{date}/inspectors/{inspectorId}/waypoints",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointsCTO updateWayPoints(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId) {
    return new RouteWaypointsCTO();
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/waypoints/{wayPointId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO findWayPointById(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId,
                                          @PathVariable("wayPointId") Long wayPointId) {
    return new RouteWaypointTO();
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "{date}/inspectors/{inspectorId}/waypoints/{wayPointId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RouteWaypointTO updateWayPoint(@PathVariable("date") String dateOfRoutes,
                                          @PathVariable("inspectorId") Long inspectorId,
                                          @PathVariable("wayPointId") Long wayPointId) {
    return new RouteWaypointTO();
  }


  @RequestMapping(
      method = RequestMethod.GET,
      path = "{date}/inspectors/{inspectorId}/notifications",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NotificationsCTO findNotificationsByInspectorId(@PathVariable("date") String dateOfRoutes,
                                                         @PathVariable("inspectorId") Long inspectorId) {
    return new NotificationsCTO();
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "{date}/inspectors/{inspectorId}/notifications",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NotificationTO createNotification(@PathVariable("date") String dateOfRoutes,
                                           @PathVariable("inspectorId") Long inspectorId) {
    return new NotificationTO();
  }

}
