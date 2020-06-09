package de.exxcellent.student.softwarearchitecture.transition.component.user.data.repository;

import de.exxcellent.student.softwarearchitecture.transition.component.user.data.entities.UserPermissionEntity;
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
