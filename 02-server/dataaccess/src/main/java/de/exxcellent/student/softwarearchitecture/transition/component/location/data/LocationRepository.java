package de.exxcellent.student.softwarearchitecture.transition.component.location.data;

import de.exxcellent.student.softwarearchitecture.transition.component.location.data.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
