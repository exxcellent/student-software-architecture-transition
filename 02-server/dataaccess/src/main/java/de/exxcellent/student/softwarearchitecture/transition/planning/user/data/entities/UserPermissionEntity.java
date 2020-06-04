package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.EntityWithTechnicalParameters;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Entity
@IdClass(UserPermissionEntity.UserPermissionId.class)
@Table(name = "user_permission")
public class UserPermissionEntity extends EntityWithTechnicalParameters implements Serializable {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Id
  @Column(name = "permission")
  @Enumerated(EnumType.STRING)
  private PermissionName permission;

  public UserPermissionEntity() {
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
    @Column(name = "user_id")
    Long userId;

    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
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
