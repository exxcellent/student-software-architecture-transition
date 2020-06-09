package de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.CommonEntity;

import javax.persistence.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "inspector")
@Entity
public class InspectorEntity extends CommonEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;


  public InspectorEntity() {
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
