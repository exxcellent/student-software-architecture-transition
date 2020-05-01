package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.businesslogic.mapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.api.types.InspectorDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.data.entities.InspectorEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.api.types.LocationDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data.entities.LocationEntity;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class InspectorMapper {

  private InspectorMapper() {}


  public static final Function<InspectorEntity, InspectorDO> toInspectorDO = entity -> {
    var inspectorDO = new InspectorDO();

    inspectorDO.setInspectorId(entity.getId());
    inspectorDO.setVersion(entity.getVersion());
    inspectorDO.setFistName(entity.getFirstName());
    inspectorDO.setLastName(entity.getLastName());

    return inspectorDO;
  };

  public static final Function<InspectorDO, InspectorEntity> toInspectorEntity = inspectorDO -> {
    var inspectorEntity = new InspectorEntity();

    inspectorEntity.setId(inspectorDO.getInspectorId());
    inspectorEntity.setVersion(inspectorDO.getVersion());
    inspectorEntity.setFirstName(inspectorDO.getFistName());
    inspectorEntity.setLastName(inspectorDO.getLastName());

    return inspectorEntity;
  };
}
