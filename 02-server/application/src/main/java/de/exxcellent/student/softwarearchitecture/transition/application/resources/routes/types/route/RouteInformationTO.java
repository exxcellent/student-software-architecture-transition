package de.exxcellent.student.softwarearchitecture.transition.application.resources.routes.types.route;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class RouteInformationTO {

  private Long totalDurationInSeconds;
  private Long timeRemainingInSeconds;

  public RouteInformationTO() {
  }

  public Long getTotalDurationInSeconds() {
    return totalDurationInSeconds;
  }

  public void setTotalDurationInSeconds(Long totalDurationInSeconds) {
    this.totalDurationInSeconds = totalDurationInSeconds;
  }

  public Long getTimeRemainingInSeconds() {
    return timeRemainingInSeconds;
  }

  public void setTimeRemainingInSeconds(Long timeRemainingInSeconds) {
    this.timeRemainingInSeconds = timeRemainingInSeconds;
  }
}
