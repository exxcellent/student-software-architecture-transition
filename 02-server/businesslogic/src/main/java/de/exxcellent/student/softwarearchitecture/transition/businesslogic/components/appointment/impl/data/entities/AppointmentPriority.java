package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.appointment.impl.data.entities;

/**
 * - Prio 1: Gefahr/sicherheitsrelevante Leitungen (100kV, Hauptwasserleitung, Gashochdruck)
 * – Prio 2: dringliche Baustellen (warten, müssen dringend beginnen)
 * (– Prio 3: Wegstrecke, Sondernutzungen (siehe Typ))
 * – Prio 4: Kontrolltermine für laufende Maßnahmen
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public enum AppointmentPriority {
  DANGEROUS, // prio 1
  URGENT, // prio 2
  NORMAL // prio 3,4
}
