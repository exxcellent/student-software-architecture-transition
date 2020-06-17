package de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class AnalyticsFile {
  private DependencyFile file;
  private Long numberOfDependenciesInSameModule;
  private Long numberOfDependenciesInSamePackage;
  private Long numberOfDependenciesInSameComponent;
  private Long numberOfDependenciesInDifferentModule;
  private Long numberOfDependenciesInDifferentPackage;
  private Long numberOfDependenciesInDifferentComponent;

  public AnalyticsFile(DependencyFile originalFile) {
    this.file = originalFile;
  }

  public DependencyFile getFile() {
    return file;
  }

  public void setFile(DependencyFile file) {
    this.file = file;
  }

  public Long getNumberOfDependenciesInSameModule() {
    return numberOfDependenciesInSameModule;
  }

  public void setNumberOfDependenciesInSameModule(Long numberOfDependenciesInSameModule) {
    this.numberOfDependenciesInSameModule = numberOfDependenciesInSameModule;
  }

  public Long getNumberOfDependenciesInSamePackage() {
    return numberOfDependenciesInSamePackage;
  }

  public void setNumberOfDependenciesInSamePackage(Long numberOfDependenciesInSamePackage) {
    this.numberOfDependenciesInSamePackage = numberOfDependenciesInSamePackage;
  }

  public Long getNumberOfDependenciesInSameComponent() {
    return numberOfDependenciesInSameComponent;
  }

  public void setNumberOfDependenciesInSameComponent(Long numberOfDependenciesInSameComponent) {
    this.numberOfDependenciesInSameComponent = numberOfDependenciesInSameComponent;
  }

  public Long getNumberOfDependenciesInDifferentModule() {
    return numberOfDependenciesInDifferentModule;
  }

  public void setNumberOfDependenciesInDifferentModule(Long numberOfDependenciesInDifferentModule) {
    this.numberOfDependenciesInDifferentModule = numberOfDependenciesInDifferentModule;
  }

  public Long getNumberOfDependenciesInDifferentPackage() {
    return numberOfDependenciesInDifferentPackage;
  }

  public void setNumberOfDependenciesInDifferentPackage(Long numberOfDependenciesInDifferentPackage) {
    this.numberOfDependenciesInDifferentPackage = numberOfDependenciesInDifferentPackage;
  }

  public Long getNumberOfDependenciesInDifferentComponent() {
    return numberOfDependenciesInDifferentComponent;
  }

  public void setNumberOfDependenciesInDifferentComponent(Long numberOfDependenciesInDifferentComponent) {
    this.numberOfDependenciesInDifferentComponent = numberOfDependenciesInDifferentComponent;
  }
}
