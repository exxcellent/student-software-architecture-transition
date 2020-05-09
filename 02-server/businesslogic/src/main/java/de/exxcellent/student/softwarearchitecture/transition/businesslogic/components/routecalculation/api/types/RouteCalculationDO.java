package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types;

import java.util.List;

public class RouteCalculationDO {

  private List<LocationResponseDO> sortedPositions;
  private Long travelDurationInSeconds;

  public RouteCalculationDO() {
  }

  public List<LocationResponseDO> getSortedPositions() {
    return sortedPositions;
  }

  public void setSortedPositions(List<LocationResponseDO> sortedPositions) {
    this.sortedPositions = sortedPositions;
  }

  public Long getTravelDurationInSeconds() {
    return travelDurationInSeconds;
  }

  public void setTravelDurationInSeconds(Long travelDurationInSeconds) {
    this.travelDurationInSeconds = travelDurationInSeconds;
  }
}
