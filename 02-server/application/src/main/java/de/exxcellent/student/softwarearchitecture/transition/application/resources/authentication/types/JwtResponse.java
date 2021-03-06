package de.exxcellent.student.softwarearchitecture.transition.application.resources.authentication.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class JwtResponse implements ReadOnlyTO {

  private String jwtToken;

  public JwtResponse() {
  }

  public JwtResponse(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
