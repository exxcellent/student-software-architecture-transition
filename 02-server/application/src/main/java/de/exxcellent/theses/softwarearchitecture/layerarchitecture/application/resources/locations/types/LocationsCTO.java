package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.resources.locations.types;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.resources.common.BusinessTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationsCTO {
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
