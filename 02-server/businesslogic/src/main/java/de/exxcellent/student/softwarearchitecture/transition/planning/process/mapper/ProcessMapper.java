package de.exxcellent.student.softwarearchitecture.transition.planning.process.mapper;

import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.Priority;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.ProcessDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.Type;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.entities.ProcessEntity;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.entities.ProcessPriority;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.data.entities.ProcessType;

import java.util.function.Function;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ProcessMapper {

  private ProcessMapper() {}

  public static final Function<ProcessEntity, ProcessDO> toProcessDO = entity -> {
    var processDO = new ProcessDO();

    processDO.setProcessId(entity.getId());
    processDO.setVersion(entity.getVersion());

    processDO.setContactId(entity.getContactId());
    processDO.setInspectorId(entity.getInspectorId());
    processDO.setLocationId(entity.getLocationId());

    processDO.setTitle(entity.getTitle());
    processDO.setPriority(ProcessMapper.toPriority.apply(entity.getProcessPriority()));
    processDO.setType(ProcessMapper.toType.apply(entity.getProcessType()));

    return processDO;
  };

  public static final Function<ProcessDO, ProcessEntity> toProcessEntity = processDO -> {
    var processEntity = new ProcessEntity();

    processEntity.setId(processDO.getProcessId());
    processEntity.setVersion(processDO.getVersion());

    processEntity.setContactId(processDO.getContactId());
    processEntity.setInspectorId(processDO.getInspectorId());
    processEntity.setLocationId(processDO.getLocationId());

    processEntity.setTitle(processDO.getTitle());
    processEntity.setProcessPriority(ProcessMapper.fromPriority.apply(processDO.getPriority()));
    processEntity.setProcessType(ProcessMapper.fromType.apply(processDO.getType()));

    return processEntity;
  };


  private static final Function<ProcessType, Type> toType = processType -> {
    switch (processType) {
      case INITIAL_INSTRUCTION: return Type.INITIAL_INSTRUCTION;
      case WATER: return Type.WATER;
      case ROAD_CONSTRUCTION: return Type.ROAD_CONSTRUCTION;
      case SPECIAL_USE: return Type.SPECIAL_USE;
      case SEWER_CONSTRUCTION: return Type.SEWER_CONSTRUCTION;
      case TELECOMMUNICATIONS: return Type.TELECOMMUNICATIONS;
      case NORMAL:
      default:
        return Type.NORMAL;
    }
  };

  private static final Function<Type, ProcessType> fromType = type -> {
    switch (type) {
      case INITIAL_INSTRUCTION: return ProcessType.INITIAL_INSTRUCTION;
      case WATER: return ProcessType.WATER;
      case ROAD_CONSTRUCTION: return ProcessType.ROAD_CONSTRUCTION;
      case SPECIAL_USE: return ProcessType.SPECIAL_USE;
      case SEWER_CONSTRUCTION: return ProcessType.SEWER_CONSTRUCTION;
      case TELECOMMUNICATIONS: return ProcessType.TELECOMMUNICATIONS;
      case NORMAL:
      default:
        return ProcessType.NORMAL;
    }
  };

  private static final Function<ProcessPriority, Priority> toPriority = processPriority -> {
    switch (processPriority) {
      case DANGEROUS: return Priority.DANGEROUS;
      case URGENT: return Priority.URGENT;
      case NORMAL:
      default:
        return Priority.NORMAL;
    }
  };

  private static final Function<Priority, ProcessPriority> fromPriority = priority -> {
    switch (priority) {
      case DANGEROUS: return ProcessPriority.DANGEROUS;
      case URGENT: return ProcessPriority.URGENT;
      case NORMAL:
      default:
        return ProcessPriority.NORMAL;
    }
  };
}
