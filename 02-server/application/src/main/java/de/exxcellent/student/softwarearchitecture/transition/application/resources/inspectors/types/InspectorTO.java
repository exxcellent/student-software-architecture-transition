package de.exxcellent.student.softwarearchitecture.transition.application.resources.inspectors.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class InspectorTO extends BusinessTO {

  private Long inspectorId;
  private String firstName;
  private String lastName;

  public InspectorTO() {
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
