package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class TripResponseDTO {

  private String departure_time;
  private float travel_time;
  private List<LocationResponseDTO> locations;

  public TripResponseDTO() {
  }

  public String getDeparture_time() {
    return departure_time;
  }

  public void setDeparture_time(String departure_time) {
    this.departure_time = departure_time;
  }

  public float getTravel_time() {
    return travel_time;
  }

  public void setTravel_time(float travel_time) {
    this.travel_time = travel_time;
  }

  public List<LocationResponseDTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationResponseDTO> locations) {
    this.locations = locations;
  }
}
