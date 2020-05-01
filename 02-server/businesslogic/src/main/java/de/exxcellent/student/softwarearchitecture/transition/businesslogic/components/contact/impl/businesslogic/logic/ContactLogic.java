package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.businesslogic.logic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.data.ContactRepository;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.data.entities.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class ContactLogic extends CrudLogic<ContactEntity> {

  @Autowired
  public ContactLogic(ContactRepository contactRepository, DateTimeUtil dateTimeUtil) {
    super(contactRepository, dateTimeUtil);
  }

}
