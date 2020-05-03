package de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class InspectorsCTO implements ReadOnlyTO {

  private List<InspectorTO> inspectors;

  public InspectorsCTO() {
  }

  public InspectorsCTO(List<InspectorTO> inspectors) {
    this.inspectors = inspectors;
  }

  public List<InspectorTO> getInspectors() {
    return inspectors;
  }

  public void setInspectors(List<InspectorTO> inspectors) {
    this.inspectors = inspectors;
  }
}
