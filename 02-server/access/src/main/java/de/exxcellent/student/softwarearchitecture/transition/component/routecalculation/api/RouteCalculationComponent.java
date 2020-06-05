package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.api;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.api.types.RouteCalculationDO;

import java.util.List;

public interface RouteCalculationComponent {

  RouteCalculationDO calculateRoute(List<LocationRequestDO> positions, CalculationMode calculationMode);
}
