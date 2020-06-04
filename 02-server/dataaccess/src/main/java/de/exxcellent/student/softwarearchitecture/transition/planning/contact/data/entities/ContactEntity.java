package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.common.data.entities.CommonEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "contact")
@Entity
public class ContactEntity extends CommonEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Column(name = "phone_number")
  private String phoneNumber;

  @Email
  @Column(name = "email")
  private String email;


  public ContactEntity() {
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
