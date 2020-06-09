package de.exxcellent.student.softwarearchitecture.transition.component.inspector.data;

import de.exxcellent.student.softwarearchitecture.transition.component.inspector.data.entities.InspectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface InspectorRepository extends JpaRepository<InspectorEntity, Long> {
}
