package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.RouteCalculationDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.connector.RouteCalculationConnector;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.connector.types.TripResponseTO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.connector.types.TripTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteCalculationLogic {

   private final RouteCalculationConnector routeCalculationConnector;

  @Autowired
  public RouteCalculationLogic(RouteCalculationConnector routeCalculationConnector) {
    this.routeCalculationConnector = routeCalculationConnector;
  }

  public TripResponseTO calculateRoute(TripTO tripTO) {
    return routeCalculationConnector.calculateTrip(tripTO);
  }
}
