package de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic;

import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types.AnalyticsFile;
import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types.DependencyFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class Aggregator {

  public List<AnalyticsFile> aggregateFiles(List<DependencyFile> files) {

    Map<String, DependencyFile> filePathIndex = new HashMap<>();
    Map<String, Long> filePathNumberOfDepsInSameModule = new HashMap<>();
    Map<String, Long> filePathNumberOfDepsInSamePackage = new HashMap<>();
    Map<String, Long> filePathNumberOfDepsInSameComponent = new HashMap<>();

    Map<String, Long> filePathNumberOfDepsInDifferentModule = new HashMap<>();
    Map<String, Long> filePathNumberOfDepsInDifferentPackage = new HashMap<>();
    Map<String, Long> filePathNumberOfDepsInDifferentComponent = new HashMap<>();

    files.forEach(file -> {
      filePathIndex.put(file.getPath(), file);

      var numberOfDepsInSameModule = file.getDependencies().stream()
          .filter(dep -> dep.getModule() != null)
          .filter(dep -> dep.getModule().equals(file.getModule())).count();
      filePathNumberOfDepsInSameModule.put(file.getPath(), numberOfDepsInSameModule);

      var numberOfDepsInSamePackage = file.getDependencies().stream()
          .filter(dep -> dep.getPackage() != null)
          .filter(dep -> dep.getPackage().equals(file.getPackage())).count();
      filePathNumberOfDepsInSamePackage.put(file.getPath(), numberOfDepsInSamePackage);

      var numberOfDepsInSameComponent = file.getDependencies().stream()
          .filter(dep -> dep.getComponent() != null)
          .filter(dep -> dep.getComponent().equals(file.getComponent())).count();
      filePathNumberOfDepsInSameComponent.put(file.getPath(), numberOfDepsInSameComponent);

      var numberOfDepsInDifferentModule = file.getDependencies().stream()
          .filter(dep -> dep.getModule() != null)
          .filter(dep -> !dep.getModule().equals(file.getModule())).count();
      filePathNumberOfDepsInDifferentModule.put(file.getPath(), numberOfDepsInDifferentModule);

      var numberOfDepsInDifferentPackage = file.getDependencies().stream()
          .filter(dep -> dep.getPackage() != null)
          .filter(dep -> !dep.getPackage().equals(file.getPackage())).count();
      filePathNumberOfDepsInDifferentPackage.put(file.getPath(), numberOfDepsInDifferentPackage);

      var numberOfDepsInDifferentComponent = file.getDependencies().stream()
          .filter(dep -> dep.getComponent() != null)
          .filter(dep -> !dep.getComponent().equals(file.getComponent())).count();
      filePathNumberOfDepsInDifferentComponent.put(file.getPath(), numberOfDepsInDifferentComponent);
    });

    List<AnalyticsFile> filesWithStatistics = new ArrayList<>();

    filePathNumberOfDepsInSameModule.keySet().forEach(path -> {
      var analyticsFile = new AnalyticsFile(filePathIndex.get(path));

      analyticsFile.setNumberOfDependenciesInSameModule(filePathNumberOfDepsInSameModule.get(path));
      analyticsFile.setNumberOfDependenciesInSamePackage(filePathNumberOfDepsInSamePackage.get(path));
      analyticsFile.setNumberOfDependenciesInSameComponent(filePathNumberOfDepsInSameComponent.get(path));

      analyticsFile.setNumberOfDependenciesInDifferentModule(filePathNumberOfDepsInDifferentModule.get(path));
      analyticsFile.setNumberOfDependenciesInDifferentPackage(filePathNumberOfDepsInDifferentPackage.get(path));
      analyticsFile.setNumberOfDependenciesInDifferentComponent(filePathNumberOfDepsInDifferentComponent.get(path));

      filesWithStatistics.add(analyticsFile);
    });

    return filesWithStatistics;
  }
}
