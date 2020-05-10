package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.RouteCalculationDO;

import java.util.List;

public interface RouteCalculationComponent {

  RouteCalculationDO calculateRoute(List<LocationRequestDO> positions, CalculationMode calculationMode);
}
