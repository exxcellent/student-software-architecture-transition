package de.exxcellent.student.softwarearchitecture.transition.planning.inspector.data;

import de.exxcellent.student.softwarearchitecture.transition.planning.inspector.data.entities.InspectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface InspectorRepository extends JpaRepository<InspectorEntity, Long> {
}
