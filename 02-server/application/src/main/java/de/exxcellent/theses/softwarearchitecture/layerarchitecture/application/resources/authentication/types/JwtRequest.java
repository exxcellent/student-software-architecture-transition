package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.resources.authentication.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class JwtRequest {

  private String userName;
  private String password;

  public JwtRequest() {
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
