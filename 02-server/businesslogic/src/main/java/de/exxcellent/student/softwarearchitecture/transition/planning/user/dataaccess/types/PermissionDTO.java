package de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.entities.EntityWithTechnicalParameters;

import java.io.Serializable;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class PermissionDTO extends EntityWithTechnicalParameters implements Serializable {

  private PermissionName permission;

  public PermissionDTO() {
  }

  public PermissionName getPermission() {
    return permission;
  }

  public void setPermission(PermissionName permission) {
    this.permission = permission;
  }
}
