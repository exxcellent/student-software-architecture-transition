package de.exxcellent.student.softwarearchitecture.transition.application.resources.contacts.types;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class ContactsCTO implements ReadOnlyTO {

  private List<ContactTO> contactTOs;

  public ContactsCTO() {
  }

  public ContactsCTO(List<ContactTO> contactTOs) {
    this.contactTOs = contactTOs;
  }

  public List<ContactTO> getContacts() {
    return contactTOs;
  }

  public void setContacts(List<ContactTO> contactTOs) {
    this.contactTOs = contactTOs;
  }

}
