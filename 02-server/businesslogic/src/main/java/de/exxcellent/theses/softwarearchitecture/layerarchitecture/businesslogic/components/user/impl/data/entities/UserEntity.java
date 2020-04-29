package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Entity
@Table(name = "application_user")
public class UserEntity extends CommonEntity {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  public UserEntity() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
