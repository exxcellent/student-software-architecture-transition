package de.exxcellent.student.softwarearchitecture.transition.component.process.data.repository;

import de.exxcellent.student.softwarearchitecture.transition.component.process.data.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
}
