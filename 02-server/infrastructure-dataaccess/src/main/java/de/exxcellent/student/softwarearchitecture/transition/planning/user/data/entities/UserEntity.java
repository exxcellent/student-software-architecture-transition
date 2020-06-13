package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.entities.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Entity
@Table(name = "application_user")
public class UserEntity extends CommonEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  public UserEntity() {
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
