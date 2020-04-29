package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@MappedSuperclass
public abstract class CommonEntity {

  @NotNull
  @Version
  @Column(name = "version")
  private Integer version;

  @NotNull
  @Column(name = "created_at_utc")
  protected OffsetDateTime createdAtUtc;

  @NotNull
  @Column(name = "created_by")
  protected String createdBy;

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