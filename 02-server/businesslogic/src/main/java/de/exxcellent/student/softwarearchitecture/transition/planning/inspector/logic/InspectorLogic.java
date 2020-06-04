package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspector.data.InspectorRepository;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspector.data.entities.InspectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
@Transactional
public class InspectorLogic extends CrudLogic<InspectorEntity> {

  @Autowired
  public InspectorLogic(InspectorRepository inspectorRepository, DateTimeUtil dateTimeUtil) {
    super(inspectorRepository, dateTimeUtil);
  }
}
