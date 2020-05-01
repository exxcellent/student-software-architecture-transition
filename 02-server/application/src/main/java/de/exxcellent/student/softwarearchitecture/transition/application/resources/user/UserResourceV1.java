package de.exxcellent.student.softwarearchitecture.transition.application.resources.user;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.TechnicalResource;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.UserComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@RestController
@CrossOrigin
@RequestMapping("v1/users")
public class UserResourceV1 implements TechnicalResource {

  private static final Logger LOG = LoggerFactory.getLogger(UserResourceV1.class);

  private final UserComponent userComponent;

  @Autowired
  public UserResourceV1(UserComponent userComponent) {
    this.userComponent = userComponent;
  }


  @RequestMapping(
      method = RequestMethod.GET,
      path = "{userId}/permission/{permission}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean hasPermission(@PathVariable("userId") Long userId,
                               @PathVariable("permission") String permission) {
    return userComponent.hasPermission(userId, Permission.valueOf(permission.toUpperCase()));
  }

}