package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.User;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.AppointmentComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.api.types.AppointmentDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.logic.AppointmentLogic;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.businesslogic.mapper.AppointmentMapper;

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
public class AppointmentFacade implements AppointmentComponent {

  private final AppointmentLogic appointmentLogic;

  @Autowired
  public AppointmentFacade(AppointmentLogic appointmentLogic) {
    this.appointmentLogic = appointmentLogic;
  }

    @Override
    public List<AppointmentDO> findAll() {
      var appointments = appointmentLogic.findAll();

      return appointments.stream()
          .map(AppointmentMapper.toAppointmentDO)
          .collect(Collectors.toList());
    }
  
    @Override
    public AppointmentDO findById(Long appointmentId) {
      Preconditions.checkNotNull(appointmentId, "AppointmentId must not be null");
      Preconditions.checkArgument(appointmentId > 0, "AppointmentId must be positive");

      return AppointmentMapper.toAppointmentDO.apply(appointmentLogic.findById(appointmentId));
    }
  
    @Override
    public AppointmentDO create(AppointmentDO appointmentDO, User user) {
      Preconditions.checkNull(appointmentDO.getAppointmentId(), "AppointmentId must be null");
      Preconditions.checkNull(appointmentDO.getVersion(), "AppointmentDO version must be null");

      Preconditions.checkNotNull(appointmentDO.getAppointmentType(), "AppointmentDO type must not be null");
      Preconditions.checkNotNull(appointmentDO.getAppointmentPriority(), "AppointmentDO priority must not be null");
      Preconditions.checkNotNull(appointmentDO.getDate(), "AppointmentDO date must not be null");
      Preconditions.checkNotNull(appointmentDO.getFinished(), "AppointmentDO isFinished must not be null");

      var appointmentEntity = AppointmentMapper.toAppointmentEntity.apply(appointmentDO);
      var persistedAppointmentEntity = appointmentLogic.create(appointmentEntity, user);

      return AppointmentMapper.toAppointmentDO.apply(persistedAppointmentEntity);
    }
  
    @Override
    public AppointmentDO update(AppointmentDO appointmentDO, User user) {
      Preconditions.checkNotNull(appointmentDO.getAppointmentId(), "AppointmentId must not be null");
      Preconditions.checkArgument(appointmentDO.getAppointmentId() > 0, "AppointmentId must be positive");
      Preconditions.checkNotNull(appointmentDO.getVersion(), "AppointmentDO version must not be null");
      Preconditions.checkArgument(appointmentDO.getVersion() >= 0, "AppointmentDO version must be positive");

      Preconditions.checkNotNull(appointmentDO.getAppointmentType(), "AppointmentDO type must not be null");
      Preconditions.checkNotNull(appointmentDO.getAppointmentPriority(), "AppointmentDO priority must not be null");
      Preconditions.checkNotNull(appointmentDO.getDate(), "AppointmentDO date must not be null");
      Preconditions.checkNotNull(appointmentDO.getFinished(), "AppointmentDO isFinished must not be null");

      var appointmentEntity = AppointmentMapper.toAppointmentEntity.apply(appointmentDO);
      var persistedAppointmentEntity = appointmentLogic.update(appointmentEntity, user);

      return AppointmentMapper.toAppointmentDO.apply(persistedAppointmentEntity);
    }
  
    @Override
    public void delete(AppointmentDO appointmentDO) {
      Preconditions.checkNotNull(appointmentDO.getAppointmentId(), "AppointmentId must not be null");
      Preconditions.checkArgument(appointmentDO.getAppointmentId() > 0, "AppointmentId must be positive");
      Preconditions.checkNotNull(appointmentDO.getVersion(), "AppointmentDO version must not be null");
      Preconditions.checkArgument(appointmentDO.getVersion() >= 0, "AppointmentDO version must be positive");

      var appointmentEntity = AppointmentMapper.toAppointmentEntity.apply(appointmentDO);

      appointmentLogic.delete(appointmentEntity);
    }
}
