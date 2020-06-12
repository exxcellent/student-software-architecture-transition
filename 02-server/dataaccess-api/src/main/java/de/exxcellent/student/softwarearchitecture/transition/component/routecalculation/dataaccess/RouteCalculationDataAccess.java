package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.ModeDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripResponseDTO;

public interface RouteCalculationDataAccess {

    TripResponseDTO calculateTrip(TripDTO tripDTO, ModeDTO modeDTO);
}
