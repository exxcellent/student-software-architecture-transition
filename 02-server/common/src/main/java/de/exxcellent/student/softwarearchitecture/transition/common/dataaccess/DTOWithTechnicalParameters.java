package de.exxcellent.student.softwarearchitecture.transition.common.dataaccess;

import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public abstract class DTOWithTechnicalParameters {

  private Integer version;
  protected OffsetDateTime createdAtUtc;
  protected String createdBy;
  protected OffsetDateTime lastModifiedAtUtc;
  protected String lastModifiedBy;

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public OffsetDateTime getCreatedAtUtc() {
    return createdAtUtc;
  }

  public void setCreatedAtUtc(OffsetDateTime createdAtUtc) {
    this.createdAtUtc = createdAtUtc;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public OffsetDateTime getLastModifiedAtUtc() {
    return lastModifiedAtUtc;
  }

  public void setLastModifiedAtUtc(OffsetDateTime lastModifiedAtUtc) {
    this.lastModifiedAtUtc = lastModifiedAtUtc;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }
}
