package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.resources.authentication.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class JwtResponse {

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
