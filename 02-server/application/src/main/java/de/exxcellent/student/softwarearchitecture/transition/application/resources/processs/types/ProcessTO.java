package de.exxcellent.student.softwarearchitecture.transition.application.resources.processs.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.BusinessTO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class ProcessTO extends BusinessTO {

  private Long processId;

  private Long inspectorId;
  private Long locationId;
  private Long contactId;

  private String title;
  private ProcessType type;
  private ProcessPriority priority;

  public ProcessTO() {
  }

  public Long getProcessId() {
    return processId;
  }

  public void setProcessId(Long processId) {
    this.processId = processId;
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

  public ProcessType getType() {
    return type;
  }

  public void setType(ProcessType type) {
    this.type = type;
  }

  public ProcessPriority getPriority() {
    return priority;
  }

  public void setPriority(ProcessPriority priority) {
    this.priority = priority;
  }
}
