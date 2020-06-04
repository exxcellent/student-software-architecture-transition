package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.api.ContactComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.api.types.ContactDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.businesslogic.logic.ContactLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.businesslogic.mapper.ContactMapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.inspector.impl.businesslogic.mapper.InspectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
 @Component
public class ContactFacade implements ContactComponent {

  private final ContactLogic contactLogic;

  @Autowired
  public ContactFacade(ContactLogic contactLogic) {
    this.contactLogic = contactLogic;
  }


  @Override
  public List<ContactDO> findAll() {
    var contacts = contactLogic.findAll();

    return contacts.stream()
        .map(ContactMapper.toContactDO)
        .collect(Collectors.toList());
  }

  @Override
  public ContactDO findById(Long contactId) {
    Preconditions.checkNotNull(contactId, "ContactId must not be null");
    Preconditions.checkArgument(contactId > 0, "ContactId must be positive");

    return ContactMapper.toContactDO.apply(contactLogic.findById(contactId));  }

  @Override
  public ContactDO create(ContactDO newContactDO, User user) {
    Preconditions.checkNotNull(newContactDO, "ContactDO must not be null");
    Preconditions.checkNull(newContactDO.getContactId(), "ContactId must be null");
    Preconditions.checkNull(newContactDO.getVersion(), "ContactDO version must be null");

    Preconditions.checkNotNullOrEmpty(newContactDO.getPhoneNumber(), "ContactDO phoneNumber must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var contactEntity = ContactMapper.toContactEntity.apply(newContactDO);
    var persistedContactEntity = contactLogic.create(contactEntity, user);

    return ContactMapper.toContactDO.apply(persistedContactEntity);
  }

  @Override
  public ContactDO update(ContactDO contactDO, User user) {
    Preconditions.checkNotNull(contactDO, "ContactDO must not be null");
    Preconditions.checkNotNull(contactDO.getContactId(), "ContactId must not be null");
    Preconditions.checkArgument(contactDO.getContactId() > 0, "ContactId must be positive");
    Preconditions.checkNotNull(contactDO.getVersion(), "ContactDO version must not be null");
    Preconditions.checkArgument(contactDO.getVersion() >= 0, "ContactDO version must be positive");
    Preconditions.checkNotNullOrEmpty(contactDO.getPhoneNumber(), "ContactDO phoneNumber must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var contactEntity = ContactMapper.toContactEntity.apply(contactDO);
    var persistedContactEntity = contactLogic.update(contactEntity, user);

    return ContactMapper.toContactDO.apply(persistedContactEntity);
  }

  @Override
  public void delete(Long id) {
    Preconditions.checkNotNull(id, "ContactId must not be null");
    Preconditions.checkArgument(id > 0, "ContactId must be positive");

    contactLogic.delete(id);
  }
}
