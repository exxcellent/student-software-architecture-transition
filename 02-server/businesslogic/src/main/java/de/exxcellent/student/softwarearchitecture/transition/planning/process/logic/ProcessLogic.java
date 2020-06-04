package de.exxcellent.student.softwarearchitecture.transition.planning.process.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.ProcessRepository;

import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.entities.ProcessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class ProcessLogic extends CrudLogic<ProcessEntity> {

  @Autowired
  public ProcessLogic(ProcessRepository processRepository, DateTimeUtil dateTimeUtil) {
    super(processRepository, dateTimeUtil);
  }

}
