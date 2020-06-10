package de.exxcellent.student.softwarearchitecture.transition.component.inspector.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.inspector.api.types.InspectorDO;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.types.InspectorDTO;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class InspectorMapper {

  private InspectorMapper() {}


  public static final Function<InspectorDTO, InspectorDO> toInspectorDO = entity -> {
    var inspectorDO = new InspectorDO();

    inspectorDO.setInspectorId(entity.getId());
    inspectorDO.setVersion(entity.getVersion());
    inspectorDO.setFistName(entity.getFirstName());
    inspectorDO.setLastName(entity.getLastName());

    return inspectorDO;
  };

  public static final Function<InspectorDO, InspectorDTO> toInspectorEntity = inspectorDO -> {
    var InspectorDTO = new InspectorDTO();

    InspectorDTO.setId(inspectorDO.getInspectorId());
    InspectorDTO.setVersion(inspectorDO.getVersion());
    InspectorDTO.setFirstName(inspectorDO.getFistName());
    InspectorDTO.setLastName(inspectorDO.getLastName());

    return InspectorDTO;
  };
}
