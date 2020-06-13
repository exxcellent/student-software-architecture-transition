package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.logic;

import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.RouteCalculationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.ModeDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteCalculationLogic {

   private final RouteCalculationDataAccess routeCalculationDataAccess;

  @Autowired
  public RouteCalculationLogic(RouteCalculationDataAccess routeCalculationDataAccess) {
    this.routeCalculationDataAccess = routeCalculationDataAccess;
  }

  public TripResponseDTO calculateRoute(TripDTO tripDTO, ModeDTO modeDTO) {
    return routeCalculationDataAccess.calculateTrip(tripDTO, modeDTO);
  }
}
