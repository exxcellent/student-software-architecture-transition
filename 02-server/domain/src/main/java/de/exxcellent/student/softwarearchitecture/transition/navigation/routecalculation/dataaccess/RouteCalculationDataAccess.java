package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.ModeDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripResponseDTO;

public interface RouteCalculationDataAccess {

    TripResponseDTO calculateTrip(TripDTO tripDTO, ModeDTO modeDTO);
}
