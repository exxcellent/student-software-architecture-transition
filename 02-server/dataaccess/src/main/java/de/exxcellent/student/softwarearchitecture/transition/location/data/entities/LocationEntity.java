package de.exxcellent.student.softwarearchitecture.transition.location.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.CommonEntity;

import javax.persistence.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "location")
@Entity
public class LocationEntity extends CommonEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "street")
  private String street;

  @Column(name = "zip")
  private String zip;

  @Column(name = "city")
  private String city;

  @Column(name = "latitude")
  private float latitude;

  @Column(name = "longitude")
  private float longitude;

  public LocationEntity() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
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
