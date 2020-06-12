package de.exxcellent.student.softwarearchitecture.transition.planning.contact.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.contact.api.types.ContactDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.contact.dataaccess.types.ContactDTO;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ContactMapper {

  private ContactMapper() {}

  public static final Function<ContactDTO, ContactDO> toContactDO = entity -> {
    var contactDO = new ContactDO();

    contactDO.setContactId(entity.getId());
    contactDO.setVersion(entity.getVersion());
    contactDO.setFirstName(entity.getFirstName());
    contactDO.setLastName(entity.getLastName());
    contactDO.setEmail(entity.getEmail());
    contactDO.setPhoneNumber(entity.getPhoneNumber());

    return contactDO;
  };

  public static final Function<ContactDO, ContactDTO> toContactEntity = contactDO -> {
    var ContactDTO = new ContactDTO();

    ContactDTO.setId(contactDO.getContactId());
    ContactDTO.setVersion(contactDO.getVersion());
    ContactDTO.setFirstName(contactDO.getFirstName());
    ContactDTO.setLastName(contactDO.getLastName());
    ContactDTO.setEmail(contactDO.getEmail());
    ContactDTO.setPhoneNumber(contactDO.getPhoneNumber());

    return ContactDTO;
  };
}
