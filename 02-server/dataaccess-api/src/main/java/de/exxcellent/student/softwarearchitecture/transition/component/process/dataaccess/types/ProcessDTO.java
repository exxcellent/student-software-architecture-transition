package de.exxcellent.student.softwarearchitecture.transition.component.process.dataaccess.types;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CommonDTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class ProcessDTO extends CommonDTO {

  private Long inspectorId;

  private Long locationId;

  private Long contactId;

  private String title;

  private ProcessType processType;

  private ProcessPriority processPriority;


  public ProcessDTO() {
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
