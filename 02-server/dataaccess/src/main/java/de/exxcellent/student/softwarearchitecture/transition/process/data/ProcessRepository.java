package de.exxcellent.student.softwarearchitecture.transition.process.data;

import de.exxcellent.student.softwarearchitecture.transition.process.data.entities.ProcessEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
}
