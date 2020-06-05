package de.exxcellent.student.softwarearchitecture.transition.user.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.EntityWithTechnicalParameters;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Entity
@Table(name = "permission")
public class PermissionEntity extends EntityWithTechnicalParameters implements Serializable {

  @Id
  @Column(name = "name")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Enumerated(EnumType.STRING)
  private PermissionName permission;

  public PermissionEntity() {
  }

  public PermissionName getPermission() {
    return permission;
  }

  public void setPermission(PermissionName permission) {
    this.permission = permission;
  }
}
