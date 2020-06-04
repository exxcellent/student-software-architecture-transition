package de.exxcellent.student.softwarearchitecture.transition.planning.inspector.api.types;

import de.exxcellent.student.softwarearchitecture.transition.common.types.BusinessDO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class InspectorDO extends BusinessDO {

  private Long inspectorId;
  private String fistName;
  private String lastName;

  public InspectorDO() {
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public String getFistName() {
    return fistName;
  }

  public void setFistName(String fistName) {
    this.fistName = fistName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
