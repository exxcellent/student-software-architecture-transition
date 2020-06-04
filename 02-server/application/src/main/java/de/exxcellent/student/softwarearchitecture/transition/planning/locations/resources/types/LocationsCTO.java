package de.exxcellent.student.softwarearchitecture.transition.planning.locations.resources.types;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationsCTO implements ReadOnlyTO {
  private List<LocationTO> locations;

  public LocationsCTO() {
  }

  public List<LocationTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationTO> locations) {
    this.locations = locations;
  }
}
