package de.exxcellent.student.softwarearchitecture.transition.planning.process.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.dataaccess.ProcessDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.dataaccess.types.ProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class ProcessLogic extends CrudLogic<ProcessDTO> {

  @Autowired
  public ProcessLogic(ProcessDataAccess processDataAccess) {
    super(processDataAccess);
  }

}
