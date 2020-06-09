package de.exxcellent.student.softwarearchitecture.transition.component.process.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.entities.CommonEntity;

import javax.persistence.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "process")
@Entity
public class ProcessEntity extends CommonEntity {

  @Column(name = "inspector_id")
  private Long inspectorId;

  @Column(name = "location_id")
  private Long locationId;

  @Column(name = "contact_id")
  private Long contactId;

  @Column(name = "title")
  private String title;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private ProcessType processType;

  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  private ProcessPriority processPriority;


  public ProcessEntity() {
  }

  public Long getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(Long inspectorId) {
    this.inspectorId = inspectorId;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProcessType getProcessType() {
    return processType;
  }

  public void setProcessType(ProcessType processType) {
    this.processType = processType;
  }

  public ProcessPriority getProcessPriority() {
    return processPriority;
  }

  public void setProcessPriority(ProcessPriority processPriority) {
    this.processPriority = processPriority;
  }
}
