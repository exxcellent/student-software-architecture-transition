package de.exxcellent.student.softwarearchitecture.transition.component.contact.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.dataaccess.ContactDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.dataaccess.types.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
 @Transactional
public class ContactLogic extends CrudLogic<ContactDTO> {

  @Autowired
  public ContactLogic(ContactDataAccess contactDataAccess) {
    super(contactDataAccess);
  }

}
