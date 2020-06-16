package de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class DependencyFile {
  private String path;
  private String filePackage;
  private String className;
  private String module;
  private String domain;
  private String component;
  private String fileName;
  private List<DependencyFile> dependencies = new ArrayList<>();

  public DependencyFile(String path) {
    this.path = path;
  }

  public DependencyFile(String path, List<DependencyFile> dependencies) {
    this.path = path;
    this.dependencies = dependencies;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<DependencyFile> getDependencies() {
    return dependencies;
  }

  public void addDependency(DependencyFile dependency) {
    this.dependencies.add(dependency);
  }

  public boolean hasDependencies() {
    return dependencies != null && !dependencies.isEmpty();
  }

  public boolean isJavaFile() {
    return path.endsWith(".java");
  }

  public String getPackage() {
    return filePackage;
  }

  public void setPackage(String filePackage) {
    this.filePackage = filePackage;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public void setDependencies(List<DependencyFile> dependencies) {
    this.dependencies = dependencies;
  }
}
