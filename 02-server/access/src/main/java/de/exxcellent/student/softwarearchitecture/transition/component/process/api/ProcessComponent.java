package de.exxcellent.student.softwarearchitecture.transition.component.process.api;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.process.api.types.ProcessDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface ProcessComponent extends BusinessComponent {

  List<ProcessDO> findAll();

  ProcessDO findById(Long processId);

  ProcessDO create(ProcessDO newprocessDO, User user);

  ProcessDO update(ProcessDO processDO, User user);

  void delete(Long id);
}
