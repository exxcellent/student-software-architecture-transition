package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.dataaccess.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public enum Mode {
  BRUTE_FORCE("brute_force"),
  NONE("none"),
  RANDOM("random");

  private final String value;
  Mode(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
