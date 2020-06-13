package de.exxcellent.student.softwarearchitecture.transition.planning.user.data.repository;

import de.exxcellent.student.softwarearchitecture.transition.planning.user.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByName(String userName);

}
