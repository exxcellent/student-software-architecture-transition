package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.impl.data;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.impl.data.entities.UserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, Long> {

  List<UserPermissionEntity> findAllByUserId(Long userId);
}
