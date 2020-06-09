package de.exxcellent.student.softwarearchitecture.transition.component.user.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.entities.CommonEntity;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class UserDTO extends CommonEntity {

  private String name;

  private String password;

  public UserDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
