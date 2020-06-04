package de.exxcellent.student.softwarearchitecture.transition.common.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@MappedSuperclass
public abstract class CommonEntity extends EntityWithTechnicalParameters {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
