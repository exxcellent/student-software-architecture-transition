package de.exxcellent.student.softwarearchitecture.transition.component.appointments.resources.types;

import de.exxcellent.student.softwarearchitecture.transition.resources.common.ReadOnlyTO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AppointmentsCTO implements ReadOnlyTO {

  private List<AppointmentTO> appointmentTOs;

  public AppointmentsCTO() {
  }

  public AppointmentsCTO(List<AppointmentTO> appointmentTOs) {
    this.appointmentTOs = appointmentTOs;
  }

  public List<AppointmentTO> getAppointments() {
    return appointmentTOs;
  }

  public void setAppointments(List<AppointmentTO> appointmentTOs) {
    this.appointmentTOs = appointmentTOs;
  }

}
