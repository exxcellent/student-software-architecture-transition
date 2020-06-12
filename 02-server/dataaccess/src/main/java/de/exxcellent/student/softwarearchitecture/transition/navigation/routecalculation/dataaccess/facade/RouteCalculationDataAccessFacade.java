package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.RouteCalculationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.connector.RouteCalculationConnector;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.mapper.RouteCalculationDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.ModeDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.TripResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteCalculationDataAccessFacade implements RouteCalculationDataAccess {

    private final RouteCalculationDataAccessMapperImpl mapper;
    private final RouteCalculationConnector routeCalculationConnector;

    @Autowired
    public RouteCalculationDataAccessFacade(RouteCalculationConnector routeCalculationConnector,
                                            DateTimeUtil dateTimeUtil,
                                            RouteCalculationDataAccessMapperImpl mapper) {
        this.mapper = mapper;
        this.routeCalculationConnector = routeCalculationConnector;
    }

    @Override
    public TripResponseDTO calculateTrip(TripDTO tripDTO, ModeDTO modeDTO) {
        var tripTO = mapper.toTO(tripDTO);
        var modeTO = mapper.toModeTO(modeDTO);
        var response = routeCalculationConnector.calculateTrip(tripTO, modeTO);
        return mapper.toDTOResponse(response);
    }
}
