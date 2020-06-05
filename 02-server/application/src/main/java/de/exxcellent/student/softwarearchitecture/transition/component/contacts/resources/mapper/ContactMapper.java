package de.exxcellent.student.softwarearchitecture.transition.component.contacts.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.contacts.resources.types.ContactTO;
import de.exxcellent.student.softwarearchitecture.transition.component.contacts.resources.types.ContactsCTO;
import de.exxcellent.student.softwarearchitecture.transition.contact.api.types.ContactDO;

import java.util.function.Function;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ContactMapper {

  private ContactMapper() {}

  public static final Function<List<ContactDO>, ContactsCTO> toContactsCTO = contactDOList -> {
    var contactTOs = contactDOList.stream()
        .map(ContactMapper.toContactTO)
        .collect(Collectors.toList());

    return new ContactsCTO(contactTOs);
  };

  public static final Function<ContactDO, ContactTO> toContactTO = contactDO -> {
    var contactTO = new ContactTO();

    contactTO.setContactId(contactDO.getContactId());
    contactTO.setVersion(contactDO.getVersion());
    contactTO.setFirstName(contactDO.getFirstName());
    contactTO.setLastName(contactDO.getLastName());
    contactTO.setEmail(contactDO.getEmail());
    contactTO.setPhoneNumber(contactDO.getPhoneNumber());

    return contactTO;
  };

  public static final Function<ContactTO, ContactDO> toContactDO = contactTO -> {
      var contactDO = new ContactDO();

      contactDO.setContactId(contactTO.getContactId());
      contactDO.setVersion(contactTO.getVersion());
      contactDO.setFirstName(contactTO.getFirstName());
      contactDO.setLastName(contactTO.getLastName());
      contactDO.setEmail(contactTO.getEmail());
      contactDO.setPhoneNumber(contactTO.getPhoneNumber());

      return contactDO;
    };
}
