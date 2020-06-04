package de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@MappedSuperclass
public abstract class EntityWithTechnicalParameters {

  @NotNull
  @Version
  @Column(name = "version")
  private Integer version;

  @NotNull
  @PastOrPresent
  @Column(name = "created_at_utc")
  protected OffsetDateTime createdAtUtc;

  @NotNull
  @Column(name = "created_by")
  protected String createdBy;

  @PastOrPresent
  @Column(name = "last_modified_at_utc")
  protected OffsetDateTime lastModifiedAtUtc;

  @Column(name = "last_modified_by")
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
