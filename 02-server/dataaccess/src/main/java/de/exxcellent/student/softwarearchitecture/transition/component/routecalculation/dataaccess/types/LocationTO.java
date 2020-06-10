package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationTO {

  private int id;
  private int _index;
  private float lat;
  private float lng;
  private int duration;

  public LocationTO() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int get_index() {
    return _index;
  }

  public void set_index(int _index) {
    this._index = _index;
  }

  public float getLat() {
    return lat;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public float getLng() {
    return lng;
  }

  public void setLng(float lng) {
    this.lng = lng;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}
