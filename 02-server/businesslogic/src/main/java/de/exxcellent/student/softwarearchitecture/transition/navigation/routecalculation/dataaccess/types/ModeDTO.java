package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public enum ModeDTO {
  BRUTE_FORCE("brute_force"),
  NONE("none"),
  RANDOM("random");

  private final String value;
  ModeDTO(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
