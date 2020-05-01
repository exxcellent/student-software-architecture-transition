package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.data.InspectorRepository;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.data.entities.InspectorEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data.entities.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class InspectorLogic extends CrudLogic<InspectorEntity> {

  @Autowired
  public InspectorLogic(InspectorRepository inspectorRepository, DateTimeUtil dateTimeUtil) {
    super(inspectorRepository, dateTimeUtil);
  }
}
