package de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.businesslogic;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.CommonEntity;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.data.User;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.datetime.DateTimeUtil;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.ErrorCode;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.errorhandling.exception.BusinessException;
import de.exxcellent.theses.softwarearchitecture.layerarchitecture.businesslogic.common.resilience.Retry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public abstract class CrudLogic<T extends CommonEntity> {

  protected final JpaRepository<T, Long> repository;
  protected final DateTimeUtil dateTimeUtil;
  
  protected CrudLogic(JpaRepository<T, Long> repository, DateTimeUtil dateTimeUtil) {
    this.repository = repository;
    this.dateTimeUtil = dateTimeUtil;
  }
  
  public List<T> findAll() {
    return Retry.execute(repository::findAll);
  }

  public T findById(Long id) {
    return Retry.execute(() -> repository.findById(id)
        .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND_ERROR,
            String.format("Entity with id '%s' not found.", id), id)));
  }

  public T create(T entity, User user) {
    entity.setCreatedAtUtc(dateTimeUtil.now());
    entity.setCreatedBy(user.getUserName());

    return saveEntity(entity);
  }

  public T update(T entity, User user) {
    entity.setLastModifiedAtUtc(dateTimeUtil.now());
    entity.setLastModifiedBy(user.getUserName());

    return saveEntity(entity);
  }

  public void delete(T entity) {
    Retry.execute(() -> { repository.delete(entity); return null; });
  }


  private T saveEntity(T entity) {
    return Retry.execute(() -> {
      try {
        return repository.saveAndFlush(entity);
      } catch (ObjectOptimisticLockingFailureException optimisticLockException) {
        // cancel retry
        throw new BusinessException(ErrorCode.ENTITY_CONFLICT_ERROR,
            "The entity has already been updated. Please reload the entity and make the changes again.");
      }
    });
  }
}
