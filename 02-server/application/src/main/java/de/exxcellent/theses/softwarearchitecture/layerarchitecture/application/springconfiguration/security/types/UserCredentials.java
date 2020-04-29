package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.springconfiguration.security.types;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class UserCredentials implements UserDetails {

  private static final String PASSWORD = "thesis";

  private String userName;

  public UserCredentials(String userName) {
    this.userName = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Set.of();
  }

  @Override
  public String getPassword() {
    return PASSWORD;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
