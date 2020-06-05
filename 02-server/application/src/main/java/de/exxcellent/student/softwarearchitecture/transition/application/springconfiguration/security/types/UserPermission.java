package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.types;

import de.exxcellent.student.softwarearchitecture.transition.user.api.types.Permission;
import org.springframework.security.core.GrantedAuthority;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class UserPermission implements GrantedAuthority {

  private Permission permission;

  public UserPermission(Permission permission) {
    this.permission = permission;
  }

  @Override
  public String getAuthority() {
    return permission.name();
  }
}
