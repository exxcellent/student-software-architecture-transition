package de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api;

import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.routelocation.api.types.RouteCalculationDO;

import java.util.List;

public interface RouteCalculationComponent {

  RouteCalculationDO calculateRoute(List<LocationRequestDO> positions, CalculationMode calculationMode);
}
