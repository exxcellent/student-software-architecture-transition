package de.exxcellent.student.softwarearchitecture.transition.common.dataaccess;

import de.exxcellent.student.softwarearchitecture.transition.common.datetime.DateTimeUtil;
import de.exxcellent.student.softwarearchitecture.transition.common.entities.CommonEntity;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.common.resilience.Retry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public abstract class CrudDataAccessFacade<T extends CommonEntity> {

  protected final JpaRepository<T, Long> repository;
  protected final DateTimeUtil dateTimeUtil;

  protected CrudDataAccessFacade(JpaRepository<T, Long> repository, DateTimeUtil dateTimeUtil) {
    this.repository = repository;
    this.dateTimeUtil = dateTimeUtil;
  }
  
  public List<T> findAllEntities() {
    return Retry.execute(repository::findAll);
  }

  public T findEntityById(Long id) {
    return Retry.execute(() -> repository.findById(id)
        .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND_ERROR,
        String.format("Entity with id '%s' not found.", id), id)));
  }

  @Transactional
  @Modifying
  public T createEntity(T entity, User user) {
    entity.setCreatedAtUtc(dateTimeUtil.now());
    entity.setCreatedBy(user.getUserName());

    return saveEntity(entity);
  }

  @Transactional
  @Modifying
  public T updateEntity(T entity, User user) {
    // find existing entity to merge the managed and the unmanaged entity
    var existingEntity = findEntityById(entity.getId());

    entity.setCreatedAtUtc(existingEntity.getCreatedAtUtc());
    entity.setCreatedBy(existingEntity.getCreatedBy());
    entity.setLastModifiedAtUtc(dateTimeUtil.now());
    entity.setLastModifiedBy(user.getUserName());

    return saveEntity(entity);
  }

  @Transactional
  @Modifying
  public void deleteEntity(Long id) {
    Retry.execute(() -> {
      repository.deleteById(id); return null; });
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
