package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.api.ProcessComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.api.types.ProcessDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.businesslogic.logic.ProcessLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.process.impl.businesslogic.mapper.ProcessMapper;

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
public class ProcessFacade implements ProcessComponent {

  private final ProcessLogic processLogic;

  @Autowired
  public ProcessFacade(ProcessLogic processLogic) {
    this.processLogic = processLogic;
  }

    @Override
    public List<ProcessDO> findAll() {
      var appointments = processLogic.findAll();

      return appointments.stream()
          .map(ProcessMapper.toProcessDO)
          .collect(Collectors.toList());
    }
  
    @Override
    public ProcessDO findById(Long processId) {
      Preconditions.checkNotNull(processId, "ProcessId must not be null");
      Preconditions.checkArgument(processId > 0, "ProcessId must be positive");

      var processEntity = processLogic.findById(processId);
      return ProcessMapper.toProcessDO.apply(processEntity);
    }
  
    @Override
    public ProcessDO create(ProcessDO processDO, User user) {
      Preconditions.checkNull(processDO.getProcessId(), "ProcessId must be null");
      Preconditions.checkNull(processDO.getVersion(), "ProcessDO version must be null");

      Preconditions.checkNotNull(processDO.getInspectorId(), "ProcessDO inspector reference id must not be null");
      Preconditions.checkArgument(processDO.getInspectorId() > 0, "ProcessDO inspector reference id must be positive");
      Preconditions.checkNotNull(processDO.getLocationId(), "ProcessDO location reference id must not be null");
      Preconditions.checkArgument(processDO.getLocationId() > 0, "ProcessDO location reference id must be positive");
      Preconditions.checkNotNull(processDO.getContactId(), "ProcessDO contact reference id must not be null");
      Preconditions.checkArgument(processDO.getContactId() > 0, "ProcessDO contact reference id must be positive");

      Preconditions.checkNotNull(processDO.getType(), "ProcessDO type must not be null");
      Preconditions.checkNotNull(processDO.getPriority(), "ProcessDO priority must not be null");

      var processEntity = ProcessMapper.toProcessEntity.apply(processDO);
      var persistedProcessEntity = processLogic.create(processEntity, user);

      return ProcessMapper.toProcessDO.apply(persistedProcessEntity);
    }
  
    @Override
    public ProcessDO update(ProcessDO processDO, User user) {
      Preconditions.checkNotNull(processDO.getProcessId(), "ProcessId must not be null");
      Preconditions.checkArgument(processDO.getProcessId() > 0, "ProcessId must be positive");
      Preconditions.checkNotNull(processDO.getVersion(), "ProcessDO version must not be null");
      Preconditions.checkArgument(processDO.getVersion() >= 0, "ProcessDO version must be positive");

      Preconditions.checkNotNull(processDO.getInspectorId(), "ProcessDO inspector reference id must not be null");
      Preconditions.checkArgument(processDO.getInspectorId() > 0, "ProcessDO inspector reference id must be positive");
      Preconditions.checkNotNull(processDO.getLocationId(), "ProcessDO location reference id must not be null");
      Preconditions.checkArgument(processDO.getLocationId() > 0, "ProcessDO location reference id must be positive");
      Preconditions.checkNotNull(processDO.getContactId(), "ProcessDO contact reference id must not be null");
      Preconditions.checkArgument(processDO.getContactId() > 0, "ProcessDO contact reference id must be positive");

      Preconditions.checkNotNull(processDO.getType(), "ProcessDO type must not be null");
      Preconditions.checkNotNull(processDO.getPriority(), "ProcessDO priority must not be null");

      var processEntity = ProcessMapper.toProcessEntity.apply(processDO);
      var persistedProcessEntity = processLogic.update(processEntity, user);

      return ProcessMapper.toProcessDO.apply(persistedProcessEntity);
    }
  
    @Override
    public void delete(Long id) {
      Preconditions.checkNotNull(id, "ProcessId must not be null");
      Preconditions.checkArgument(id > 0, "ProcessId must be positive");

      processLogic.delete(id);
    }
}
