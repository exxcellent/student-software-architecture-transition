package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities.UserEntity;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.components.user.impl.data.entities.UserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByName(String userName);

}
