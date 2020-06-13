package de.exxcellent.student.softwarearchitecture.transition.planning.user.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.DTOWithTechnicalParameters;

import java.io.Serializable;
import java.util.Objects;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class UserPermissionDTO extends DTOWithTechnicalParameters implements Serializable {

  private Long userId;

  private PermissionName permission;

  public UserPermissionDTO() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public PermissionName getPermission() {
    return permission;
  }

  public void setPermission(PermissionName permission) {
    this.permission = permission;
  }

  static class UserPermissionId implements Serializable {
    Long userId;

    PermissionName permission;

    public UserPermissionId() {
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UserPermissionId that = (UserPermissionId) o;
      return Objects.equals(userId, that.userId) &&
          permission == that.permission;
    }

    @Override
    public int hashCode() {
      return Objects.hash(userId, permission);
    }
  }
}
