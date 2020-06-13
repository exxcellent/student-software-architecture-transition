package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class TripResponseTO {

  private String departure_time;
  private float travel_time;
  private List<LocationResponseTO> locations;

  public TripResponseTO() {
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

  public List<LocationResponseTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationResponseTO> locations) {
    this.locations = locations;
  }
}
