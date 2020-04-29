package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.entities;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.CommonEntity;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "location")
@Entity
public class LocationEntity extends CommonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "location_id")
  private Long locationId;

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

  public LocationEntity(Long locationId, String name, String street, String zip, String city, float latitude,
                        float longitude,
                        OffsetDateTime createdAtUtc, String createdBy,
                        OffsetDateTime lastModifiedAtUtc, String lastModifiedBy) {
    this.locationId = locationId;
    this.name = name;
    this.street = street;
    this.zip = zip;
    this.city = city;
    this.latitude = latitude;
    this.longitude = longitude;

    this.createdAtUtc = createdAtUtc;
    this.createdBy = createdBy;
    this.lastModifiedAtUtc = lastModifiedAtUtc;
    this.lastModifiedBy = lastModifiedBy;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
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
