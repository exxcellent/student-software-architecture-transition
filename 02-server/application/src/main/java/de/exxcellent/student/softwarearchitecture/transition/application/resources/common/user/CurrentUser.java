package de.exxcellent.student.softwarearchitecture.transition.application.resources.common.user;

import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.types.UserCredentials;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class CurrentUser {

  private CurrentUser() {}

  public static String getUserName() {
    UserCredentials userDetails = (UserCredentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userDetails.getUsername();
  }

  public static User getUser() {
    var userName = getUserName();
    return new User(userName);
  }
}
