package de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.notification;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CommonDTO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.route.dataaccess.types.waypoint.WaypointDTO;

import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class NotificationDTO extends CommonDTO {

  private WaypointDTO waypoint;

  private OffsetDateTime notifiedAt;

  private Channel channel;

  private Long arrivalIn;

  public NotificationDTO() {
  }

  public WaypointDTO getWaypoint() {
    return waypoint;
  }

  public void setWaypoint(WaypointDTO waypoint) {
    this.waypoint = waypoint;
  }

  public OffsetDateTime getNotifiedAt() {
    return notifiedAt;
  }

  public void setNotifiedAt(OffsetDateTime notifiedAt) {
    this.notifiedAt = notifiedAt;
  }

  public Channel getChannel() {
    return channel;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  public Long getArrivalIn() {
    return arrivalIn;
  }

  public void setArrivalIn(Long arrivalIn) {
    this.arrivalIn = arrivalIn;
  }
}
