package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationRequestDO {

  private long id;
  private int index;
  private float latitude;
  private float longitude;

  public LocationRequestDO() {
  }

  public LocationRequestDO(int id, int index, float latitude, float longitude) {
    this.id = id;
    this.index = index;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }
}
