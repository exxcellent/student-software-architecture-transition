package de.exxcellent.student.softwarearchitecture.transition.planning.contact.logic;

import de.exxcellent.student.softwarearchitecture.transition.common.businesslogic.CrudLogic;
import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.planning.contact.data.ContactRepository;

import de.exxcellent.student.softwarearchitecture.transition.planning.contact.data.entities.ContactEntity;
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
public class ContactLogic extends CrudLogic<ContactEntity> {

  @Autowired
  public ContactLogic(ContactRepository contactRepository, DateTimeUtil dateTimeUtil) {
    super(contactRepository, dateTimeUtil);
  }

}
