package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.connector.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class LocationResponseTO {

  private int id;
  private int _index;
  private float lat;
  private float lng;
  private int duration;
  private String eta;
  private float travel_time;

  public LocationResponseTO() {
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

  public String getEta() {
    return eta;
  }

  public void setEta(String eta) {
    this.eta = eta;
  }

  public float getTravel_time() {
    return travel_time;
  }

  public void setTravel_time(float travel_time) {
    this.travel_time = travel_time;
  }
}
