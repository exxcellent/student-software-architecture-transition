package de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic;

import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types.AnalyticsFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class CSVConverter {

  public String convertToCSV(List<AnalyticsFile> files) {
    var lines = new ArrayList<String>();
    lines.add(String.join(";", List.of(
        "Package", "Modul", "Komponent", "Domain", "Klasse",

        "Anzahl der Abhängigkeiten zu anderen Modulen",
        "Anzahl der Abhängigkeiten zu anderen Packages",
        "Anzahl der Abhängigkeiten zu anderen Komponenten",

        "Anzahl der Abhängigkeiten im gleichen Modul",
        "Anzahl der Abhängigkeiten im gleichen Package",
        "Anzahl der Abhängigkeiten in der gleichen Komponente"
    ))); // headline

    var content = files.stream()
        .map(file -> {
          List<String> cells = new ArrayList<>();

          cells.add(file.getFile().getPackage());
          cells.add(file.getFile().getModule());
          cells.add(file.getFile().getComponent());
          cells.add(file.getFile().getDomain());
          cells.add(file.getFile().getClassName());

          cells.add(String.valueOf(file.getNumberOfDependenciesInDifferentModule()));
          cells.add(String.valueOf(file.getNumberOfDependenciesInDifferentPackage()));
          cells.add(String.valueOf(file.getNumberOfDependenciesInDifferentComponent()));

          cells.add(String.valueOf(file.getNumberOfDependenciesInSameModule()));
          cells.add(String.valueOf(file.getNumberOfDependenciesInSamePackage()));
          cells.add(String.valueOf(file.getNumberOfDependenciesInSameComponent()));

          return String.join(";", cells); // line
        })
        .collect(Collectors.toList());

    lines.addAll(content);

    return String.join("\n", lines);
  }
}
