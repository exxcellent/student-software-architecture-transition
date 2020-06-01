package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.route.api.types.route;

public class UpdatedWaypointDO {

  private WaypointDO updated;
  private WaypointDO next;

  public UpdatedWaypointDO() {
  }

  public WaypointDO getUpdated() {
    return updated;
  }

  public void setUpdated(WaypointDO updated) {
    this.updated = updated;
  }

  public WaypointDO getNext() {
    return next;
  }

  public void setNext(WaypointDO next) {
    this.next = next;
  }
}
