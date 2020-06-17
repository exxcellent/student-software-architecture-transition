package de.exxcellent.student.softwarearchitecture.transition.navigation.routes.resources.types.route;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class RouteWaypointLocationTO {

  private String address;
  private Float latitude;
  private Float longitude;

  public RouteWaypointLocationTO() {
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }
}
