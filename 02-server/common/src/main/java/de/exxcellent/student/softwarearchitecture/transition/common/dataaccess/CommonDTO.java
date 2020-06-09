package de.exxcellent.student.softwarearchitecture.transition.common.dataaccess;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public abstract class CommonDTO extends DTOWithTechnicalParameters {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
