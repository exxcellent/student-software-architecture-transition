package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security;

import java.util.stream.Collectors;

import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.types.UserPermission;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserComponent userComponent;

  @Autowired
  public JwtUserDetailsService(UserComponent userComponent) {
    this.userComponent = userComponent;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var userDO = userComponent.findByName(username);

    if (userDO != null) {
      var permissions = userDO.getPermissions().stream()
          .map(UserPermission::new)
          .collect(Collectors.toList());

      return new User(userDO.getName(), userDO.getPassword(), permissions);
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
