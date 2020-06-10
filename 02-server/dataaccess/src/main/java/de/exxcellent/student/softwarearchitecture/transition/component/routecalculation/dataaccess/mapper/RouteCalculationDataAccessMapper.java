package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripResponseDTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripResponseTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.TripTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteCalculationDataAccessMapper {
  TripTO toTO(TripDTO dto);
  TripDTO toDTO(TripTO to);
  TripResponseDTO toDTOResponse(TripResponseTO to);
}