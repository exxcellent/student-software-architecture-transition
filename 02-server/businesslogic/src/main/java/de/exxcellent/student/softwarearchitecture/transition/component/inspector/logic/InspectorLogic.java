package de.exxcellent.student.softwarearchitecture.transition.component.inspector.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.InspectorDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.dataaccess.types.InspectorDTO;
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
public class InspectorLogic extends CrudLogic<InspectorDTO> {

  @Autowired
  public InspectorLogic(InspectorDataAccess inspectorDataAccess) {
    super(inspectorDataAccess);
  }
}
