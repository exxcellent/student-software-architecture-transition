package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationResponseDO extends LocationRequestDO {

  private Long travelDuration;

  public LocationResponseDO() {
  }

  public Long getTravelDuration() {
    return travelDuration;
  }

  public void setTravelDuration(Long travelDuration) {
    this.travelDuration = travelDuration;
  }
}
