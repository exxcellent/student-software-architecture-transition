package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationDO {

  private int id;
  private int index;
  private float latitude;
  private float longitude;

  public LocationDO() {
  }

  public LocationDO(int id, int index, float latitude, float longitude) {
    this.id = id;
    this.index = index;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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
