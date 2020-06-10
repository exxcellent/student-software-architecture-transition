package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class TripTO {

  private String departure_time;
  private List<LocationTO> locations;

  public TripTO() {
  }

  public String getDeparture_time() {
    return departure_time;
  }

  public void setDeparture_time(String departure_time) {
    this.departure_time = departure_time;
  }

  public List<LocationTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationTO> locations) {
    this.locations = locations;
  }
}
