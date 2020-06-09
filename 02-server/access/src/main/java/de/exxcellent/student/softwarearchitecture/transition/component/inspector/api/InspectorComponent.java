package de.exxcellent.student.softwarearchitecture.transition.component.inspector.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.api.types.InspectorDO;

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

  void delete(Long id);
}
