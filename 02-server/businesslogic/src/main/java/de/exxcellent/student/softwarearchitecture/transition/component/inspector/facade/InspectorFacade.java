package de.exxcellent.student.softwarearchitecture.transition.component.inspector.facade;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.api.InspectorComponent;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.api.types.InspectorDO;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.logic.InspectorLogic;
import de.exxcellent.student.softwarearchitecture.transition.component.inspector.mapper.InspectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class InspectorFacade implements InspectorComponent {

  private final InspectorLogic inspectorLogic;

  @Autowired
  public InspectorFacade(InspectorLogic inspectorLogic) {
    this.inspectorLogic = inspectorLogic;
  }

  @Override
  public List<InspectorDO> findAll() {
    var inspectors = inspectorLogic.findAll();

    return inspectors.stream()
        .map(InspectorMapper.toInspectorDO)
        .collect(Collectors.toList());
  }

  @Override
  public InspectorDO findById(Long inspectorId) {
    Preconditions.checkNotNull(inspectorId, "InspectorId must not be null");
    Preconditions.checkArgument(inspectorId > 0, "InspectorId must be positive");

    return InspectorMapper.toInspectorDO.apply(inspectorLogic.findById(inspectorId));
  }

  @Override
  public InspectorDO create(InspectorDO newInspectorDO, User user) {
    Preconditions.checkNotNull(newInspectorDO, "Inspector must not be null");
    Preconditions.checkNull(newInspectorDO.getInspectorId(), "InspectorId must be null");
    Preconditions.checkNull(newInspectorDO.getVersion(), "Inspector version must be null");

    Preconditions.checkNotNullOrEmpty(newInspectorDO.getFistName(), "Inspector first name must not be null");
    Preconditions.checkNotNullOrEmpty(newInspectorDO.getLastName(), "Inspector last name must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var newInspectorEntity = InspectorMapper.toInspectorEntity.apply(newInspectorDO);
    var persistedInspectorEntity = inspectorLogic.create(newInspectorEntity, user);

    return InspectorMapper.toInspectorDO.apply(persistedInspectorEntity);
  }

  @Override
  public InspectorDO update(InspectorDO inspectorDO, User user) {
    Preconditions.checkNotNull(inspectorDO, "Inspector must not be null");
    Preconditions.checkNotNull(inspectorDO.getInspectorId(), "InspectorId name must not be null");
    Preconditions.checkArgument(inspectorDO.getInspectorId() > 0, "InspectorId must be positive");
    Preconditions.checkNotNull(inspectorDO.getVersion(), "Inspector version name must not be null");
    Preconditions.checkArgument(inspectorDO.getVersion() >= 0, "Inspector version name must be positive");
    Preconditions.checkNotNullOrEmpty(inspectorDO.getFistName(), "Inspector first name must not be null");
    Preconditions.checkNotNullOrEmpty(inspectorDO.getLastName(), "Inspector last name must not be null");
    Preconditions.checkNotNull(user, "User must not be null");

    var inspectorEntity = InspectorMapper.toInspectorEntity.apply(inspectorDO);
    var updatedInspectorEntity = inspectorLogic.update(inspectorEntity, user);

    return InspectorMapper.toInspectorDO.apply(updatedInspectorEntity);  }

  @Override
  public void delete(Long id) {
    Preconditions.checkNotNull(id, "InspectorId name must not be null");
    Preconditions.checkArgument(id > 0, "InspectorId must be positive");

    inspectorLogic.delete(id);
  }
}
