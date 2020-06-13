package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class TripDTO {

  private String departure_time;
  private List<LocationDTO> locations;

  public TripDTO() {
  }

  public String getDeparture_time() {
    return departure_time;
  }

  public void setDeparture_time(String departure_time) {
    this.departure_time = departure_time;
  }

  public List<LocationDTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationDTO> locations) {
    this.locations = locations;
  }
}
