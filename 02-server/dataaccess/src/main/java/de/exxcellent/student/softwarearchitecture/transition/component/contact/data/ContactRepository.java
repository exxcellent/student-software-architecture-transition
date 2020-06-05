package de.exxcellent.student.softwarearchitecture.transition.component.contact.data;

import de.exxcellent.student.softwarearchitecture.transition.component.contact.data.entities.ContactEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
