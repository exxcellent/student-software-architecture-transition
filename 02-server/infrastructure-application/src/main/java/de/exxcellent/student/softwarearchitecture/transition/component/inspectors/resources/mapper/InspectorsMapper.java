package de.exxcellent.student.softwarearchitecture.transition.component.inspectors.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.inspectors.resources.types.InspectorTO;
import de.exxcellent.student.softwarearchitecture.transition.component.inspectors.resources.types.InspectorsCTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspector.api.types.InspectorDO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class InspectorsMapper {

  private InspectorsMapper() {}

  public static final Function<List<InspectorDO>, InspectorsCTO> toInspectorsCTO = inspectorDOList -> {
    var inspectorTOs = inspectorDOList.stream()
        .map(InspectorsMapper.toInspectorTO)
        .collect(Collectors.toList());

    var inspectorsCTO = new InspectorsCTO();
    inspectorsCTO.setInspectors(inspectorTOs);

    return inspectorsCTO;
  };

  public static final Function<InspectorDO, InspectorTO> toInspectorTO = inspectorDO -> {
    var inspectorTO = new InspectorTO();

    inspectorTO.setInspectorId(inspectorDO.getInspectorId());
    inspectorTO.setVersion(inspectorDO.getVersion());
    inspectorTO.setFirstName(inspectorDO.getFistName());
    inspectorTO.setLastName(inspectorDO.getLastName());

    return inspectorTO;
  };

  public static final Function<InspectorTO, InspectorDO> toInspectorDO = inspectorTO -> {
    var inspectorDO = new InspectorDO();

    inspectorDO.setInspectorId(inspectorTO.getInspectorId());
    inspectorDO.setVersion(inspectorTO.getVersion());
    inspectorDO.setFistName(inspectorTO.getFirstName());
    inspectorDO.setLastName(inspectorTO.getLastName());

    return inspectorDO;
  };
}
