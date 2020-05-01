package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.data.entities.CommonEntity;

import javax.persistence.*;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Table(name = "appointment")
@Entity
public class AppointmentEntity extends CommonEntity {

  public AppointmentEntity() {
  }

}
