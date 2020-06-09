package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.location.impl.data.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
