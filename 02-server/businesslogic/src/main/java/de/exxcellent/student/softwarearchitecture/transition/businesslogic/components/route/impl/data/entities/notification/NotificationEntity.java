package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.notification;

import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.entities.CommonEntity;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.impl.data.entities.waypoint.WaypointEntity;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Duration;
import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "notification")
@Entity
@TypeDef(
    typeClass = PostgreSQLIntervalType.class,
    defaultForType = Duration.class
)
public class NotificationEntity extends CommonEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "waypoint_id")
  private WaypointEntity waypoint;

  @Column(name = "notified_at")
  private OffsetDateTime notifiedAt;

  @Column(name = "channel")
  @Enumerated(EnumType.STRING)
  private Channel channel;

  @Column(name = "arrival_in")
  private Duration arrivalIn;

  public NotificationEntity() {
  }

  public WaypointEntity getWaypoint() {
    return waypoint;
  }

  public void setWaypoint(WaypointEntity waypoint) {
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

  public Duration getArrivalIn() {
    return arrivalIn;
  }

  public void setArrivalIn(Duration arrivalIn) {
    this.arrivalIn = arrivalIn;
  }
}
