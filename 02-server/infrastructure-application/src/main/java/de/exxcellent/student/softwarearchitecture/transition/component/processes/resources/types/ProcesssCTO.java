package de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class ProcesssCTO implements ReadOnlyTO {

  private List<ProcessTO> processTOs;

  public ProcesssCTO() {
  }

  public ProcesssCTO(List<ProcessTO> processTOs) {
    this.processTOs = processTOs;
  }

  public List<ProcessTO> getProcesss() {
    return processTOs;
  }

  public void setProcesss(List<ProcessTO> processTOs) {
    this.processTOs = processTOs;
  }

}
