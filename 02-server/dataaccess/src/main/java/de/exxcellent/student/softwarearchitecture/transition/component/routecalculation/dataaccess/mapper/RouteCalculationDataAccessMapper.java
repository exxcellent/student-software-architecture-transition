package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteCalculationDataAccessMapper {
  TripTO toTO(TripDTO dto);
  TripDTO toDTO(TripTO to);
  TripResponseDTO toDTOResponse(TripResponseTO to);
  ModeTO toModeTO(ModeDTO dto);
  ModeDTO toModeDTO(ModeTO to);
}