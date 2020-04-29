package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.springconfiguration.security.types;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.api.types.Permission;
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
