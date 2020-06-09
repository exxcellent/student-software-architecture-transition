package de.exxcellent.student.softwarearchitecture.transition.common.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CommonDTO;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.CrudDataAccess;
import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public abstract class CrudLogic<T extends CommonDTO> {

  protected final CrudDataAccess<T> repository;

  protected CrudLogic(CrudDataAccess<T> repository) {
    this.repository = repository;
  }
  
  public List<T> findAll() {
    return repository.findAll();
  }

  public T findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  @Modifying
  public T create(T newDTO, User user) {
    return repository.create(newDTO, user);
  }

  @Transactional
  @Modifying
  public T update(T updatedDTO, User user) {
    return repository.update(updatedDTO, user);
  }

  @Transactional
  @Modifying
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
