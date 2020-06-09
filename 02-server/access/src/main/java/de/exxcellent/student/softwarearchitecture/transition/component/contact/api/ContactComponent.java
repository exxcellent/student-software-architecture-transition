package de.exxcellent.student.softwarearchitecture.transition.component.contact.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.contact.api.types.ContactDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface ContactComponent extends BusinessComponent {

  List<ContactDO> findAll();

  ContactDO findById(Long contactId);

  ContactDO create(ContactDO newcontactDO, User user);

  ContactDO update(ContactDO contactDO, User user);

  void delete(Long id);
}
