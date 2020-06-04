package de.exxcellent.student.softwarearchitecture.transition.planning.user.resources;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.SecuredResource;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.TechnicalResource;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.UserComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.types.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Technical endpoints")
@RestController
@CrossOrigin
@RequestMapping("v1/users")
public class UserResourceV1 extends SecuredResource implements TechnicalResource {

  private static final Logger LOG = LoggerFactory.getLogger(UserResourceV1.class);

  private final UserComponent userComponent;

  @Autowired
  public UserResourceV1(UserComponent userComponent) {
    this.userComponent = userComponent;
  }


  @Operation(summary = "Check User Permission")
  @SecurityRequirement(name = "jwtAuth")
  @RequestMapping(
      method = RequestMethod.GET,
      path = "{userId}/permission/{permission}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean hasPermission(@PathVariable("userId") Long userId,
                               @PathVariable("permission") String permission) {
    return userComponent.hasPermission(userId, Permission.valueOf(permission.toUpperCase()));
  }

}
