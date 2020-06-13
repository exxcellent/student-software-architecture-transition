package de.exxcellent.student.softwarearchitecture.transition.planning.contact.data.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.contact.data.entities.ContactEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.contact.dataaccess.types.ContactDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactDataAccessMapper {
  ContactEntity toEntity(ContactDTO dto);
  ContactDTO toDTO(ContactEntity entity);
}