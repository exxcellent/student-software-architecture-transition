package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.data.ProcessRepository;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.data.entities.ProcessEntity;
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
