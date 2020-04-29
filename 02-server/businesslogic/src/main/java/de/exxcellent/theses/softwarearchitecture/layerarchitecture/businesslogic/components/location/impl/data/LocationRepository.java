package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.location.impl.data.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
