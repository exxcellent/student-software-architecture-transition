package de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.RouteCalculationComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.RouteCalculationDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.logic.RouteCalculationLogic;

import de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.mapper.RouteCalculationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

 @Component
public class RouteCalculationFacade implements RouteCalculationComponent {

  private final RouteCalculationLogic routeCalculationLogic;

  @Autowired
  public RouteCalculationFacade(RouteCalculationLogic routeCalculationLogic) {
    this.routeCalculationLogic = routeCalculationLogic;
  }


   @Override
   public RouteCalculationDO calculateRoute(List<LocationRequestDO> locationSequence, CalculationMode calculationMode) {
     Preconditions.checkNotNull(locationSequence, "Location sequence must not be null");
     Preconditions.checkArgument(locationSequence.size() > 1,
         "Location sequence must contain more than 1 location");
     Preconditions.checkNotNull(calculationMode, "CalculationMode must not be null");

     var tripTO = RouteCalculationMapper.toTripTO.apply(locationSequence);
     var mode = RouteCalculationMapper.toMode.apply(calculationMode);

     var tripResponseTO = routeCalculationLogic.calculateRoute(tripTO, mode);

     return RouteCalculationMapper.toRouteCalculationDO.apply(tripResponseTO);
   }
 }
