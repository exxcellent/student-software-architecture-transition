package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
