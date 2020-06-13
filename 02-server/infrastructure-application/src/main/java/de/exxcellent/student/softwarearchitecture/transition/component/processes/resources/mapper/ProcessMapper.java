package de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.mapper;

import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcessPriority;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcessTO;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcessType;
import de.exxcellent.student.softwarearchitecture.transition.component.processes.resources.types.ProcesssCTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.Priority;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.ProcessDO;
import de.exxcellent.student.softwarearchitecture.transition.planning.process.api.types.Type;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ProcessMapper {

  private ProcessMapper() {}

  public static final Function<List<ProcessDO>, ProcesssCTO> toProcesssCTO = processDOList -> {
    var processTOs = processDOList.stream()
        .map(ProcessMapper.toProcessTO)
        .collect(Collectors.toList());

    return new ProcesssCTO(processTOs);
  };

  public static final Function<ProcessDO, ProcessTO> toProcessTO = processDO -> {
    var processTO = new ProcessTO();

    processTO.setProcessId(processDO.getProcessId());
    processTO.setVersion(processDO.getVersion());

    processTO.setContactId(processDO.getContactId());
    processTO.setInspectorId(processDO.getInspectorId());
    processTO.setLocationId(processDO.getLocationId());

    processTO.setTitle(processDO.getTitle());
    processTO.setPriority(ProcessMapper.toProcessPriority.apply(processDO.getPriority()));
    processTO.setType(ProcessMapper.toProcessType.apply(processDO.getType()));

    return processTO;
  };

  public static final Function<ProcessTO, ProcessDO> toProcessDO = processTO -> {
      var processDO = new ProcessDO();

      processDO.setProcessId(processTO.getProcessId());
      processDO.setVersion(processTO.getVersion());

      processDO.setContactId(processTO.getContactId());
      processDO.setInspectorId(processTO.getInspectorId());
      processDO.setLocationId(processTO.getLocationId());

      processDO.setTitle(processTO.getTitle());
      processDO.setPriority(ProcessMapper.fromPriority.apply(processTO.getPriority()));
      processDO.setType(ProcessMapper.fromType.apply(processTO.getType()));

      return processDO;
    };



  private static final Function<ProcessType, Type> fromType = processType -> {
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

  private static final Function<Type, ProcessType> toProcessType = type -> {
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

  private static final Function<ProcessPriority, Priority> fromPriority = processPriority -> {
    switch (processPriority) {
      case DANGEROUS: return Priority.DANGEROUS;
      case URGENT: return Priority.URGENT;
      case NORMAL:
      default:
        return Priority.NORMAL;
    }
  };

  private static final Function<Priority, ProcessPriority> toProcessPriority = priority -> {
    switch (priority) {
      case DANGEROUS: return ProcessPriority.DANGEROUS;
      case URGENT: return ProcessPriority.URGENT;
      case NORMAL:
      default:
        return ProcessPriority.NORMAL;
    }
  };
}
