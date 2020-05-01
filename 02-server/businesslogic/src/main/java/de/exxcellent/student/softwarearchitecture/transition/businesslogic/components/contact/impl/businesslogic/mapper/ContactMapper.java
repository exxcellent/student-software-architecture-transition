package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.businesslogic.mapper;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.api.types.ContactDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.contact.impl.data.entities.ContactEntity;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ContactMapper {

  private ContactMapper() {}

  public static final Function<ContactEntity, ContactDO> toContactDO = entity -> {
    var contactDO = new ContactDO();

    contactDO.setContactId(entity.getId());
    contactDO.setVersion(entity.getVersion());
    contactDO.setFirstName(entity.getFirstName());
    contactDO.setLastName(entity.getLastName());
    contactDO.setEmail(entity.getEmail());
    contactDO.setPhoneNumber(entity.getPhoneNumber());

    return contactDO;
  };

  public static final Function<ContactDO, ContactEntity> toContactEntity = contactDO -> {
    var contactEntity = new ContactEntity();

    contactEntity.setId(contactDO.getContactId());
    contactEntity.setVersion(contactDO.getVersion());
    contactEntity.setFirstName(contactDO.getFirstName());
    contactEntity.setLastName(contactDO.getLastName());
    contactEntity.setEmail(contactDO.getEmail());
    contactEntity.setPhoneNumber(contactDO.getPhoneNumber());

    return contactEntity;
  };
}
