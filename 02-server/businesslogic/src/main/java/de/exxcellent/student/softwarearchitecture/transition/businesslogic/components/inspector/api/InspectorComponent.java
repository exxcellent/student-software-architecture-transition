package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.api;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.api.types.InspectorDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface InspectorComponent extends BusinessComponent {
  
  List<InspectorDO> findAll();

  InspectorDO findById(Long inspectorId);

  InspectorDO create(InspectorDO newInspectorDO, User user);

  InspectorDO update(InspectorDO inspectorDO, User user);

  void delete(InspectorDO inspectorDO);
}
