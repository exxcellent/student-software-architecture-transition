package de.exxcellent.student.softwarearchitecture.transition.planning.location.data.repository;

import de.exxcellent.student.softwarearchitecture.transition.planning.location.data.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
