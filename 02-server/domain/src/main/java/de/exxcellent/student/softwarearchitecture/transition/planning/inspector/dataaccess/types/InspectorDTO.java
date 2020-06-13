package de.exxcellent.student.softwarearchitecture.transition.planning.inspector.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CommonDTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class InspectorDTO extends CommonDTO {

  private String firstName;

  private String lastName;


  public InspectorDTO() {
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
