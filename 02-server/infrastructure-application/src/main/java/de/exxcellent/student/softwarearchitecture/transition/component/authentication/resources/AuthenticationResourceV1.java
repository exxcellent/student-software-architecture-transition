package de.exxcellent.student.softwarearchitecture.transition.component.authentication.resources;

import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.JwtTokenUtil;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.component.authentication.resources.types.JwtRequest;
import de.exxcellent.student.softwarearchitecture.transition.component.authentication.resources.types.JwtResponse;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.UserComponent;
import de.exxcellent.student.softwarearchitecture.transition.resources.common.TechnicalResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Tag(name = "Technical endpoints")
@RestController
@CrossOrigin
@RequestMapping("v1/authenticate")
public class AuthenticationResourceV1 implements TechnicalResource {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationResourceV1.class);

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserComponent userComponent;

  @Autowired
  public AuthenticationResourceV1(AuthenticationManager authenticationManager,
                                  JwtTokenUtil jwtTokenUtil, UserComponent userComponent) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userComponent = userComponent;
  }


  @Operation(summary = "Authenticate (generates the jwtAuth Token", description = "Username: 'ADMIN' oder 'USER' with password: 'thesis' ")
  @RequestMapping(
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JwtResponse authenticate(@RequestBody JwtRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUserName(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new BusinessException(ErrorCode.INVALID_SIGN_IN_CREDENTIALS, e);
    }

    var userDO = userComponent.findByName(authenticationRequest.getUserName());
    final String token = jwtTokenUtil.generateToken(userDO);

    return new JwtResponse(token);
  }
}
