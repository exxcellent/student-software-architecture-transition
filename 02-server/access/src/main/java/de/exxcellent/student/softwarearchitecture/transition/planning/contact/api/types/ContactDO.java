package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.api.types;

import de.exxcellent.student.softwarearchitecture.transition.common.types.BusinessDO;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class ContactDO extends BusinessDO {

  private Long contactId;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;

  public ContactDO() {
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
