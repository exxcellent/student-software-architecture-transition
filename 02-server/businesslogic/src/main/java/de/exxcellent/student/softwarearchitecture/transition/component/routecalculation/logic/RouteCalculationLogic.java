package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.logic;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.RouteCalculationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.Mode;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteCalculationLogic {

   private final RouteCalculationDataAccess routeCalculationDataAccess;

  @Autowired
  public RouteCalculationLogic(RouteCalculationDataAccess routeCalculationDataAccess) {
    this.routeCalculationDataAccess = routeCalculationDataAccess;
  }

  public TripResponseDTO calculateRoute(TripDTO tripDTO, Mode mode) {
    return routeCalculationDataAccess.calculateTrip(tripDTO, mode);
  }
}
