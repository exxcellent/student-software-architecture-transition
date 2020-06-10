package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.RouteCalculationDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.connector.RouteCalculationConnector;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.mapper.RouteCalculationDataAccessMapperImpl;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.Mode;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripResponseDTO;
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
    public TripResponseDTO calculateTrip(TripDTO tripDTO, Mode mode) {
        return null;
    }
}
