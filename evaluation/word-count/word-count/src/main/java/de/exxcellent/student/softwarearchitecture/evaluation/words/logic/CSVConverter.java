package de.exxcellent.student.softwarearchitecture.evaluation.words.logic;

import de.exxcellent.student.softwarearchitecture.evaluation.words.types.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class CSVConverter {

  public String convertToCSV(List<Word> words) {
    var lines = new ArrayList<String>();
    lines.add(String.join(";", List.of(
        "Wort", "Anzahl"
    ))); // headline

    var content = words.stream()
        .map(word -> {
          List<String> cells = new ArrayList<>();

          cells.add(word.getWord());
          cells.add(String.valueOf(word.getCount()));

          return String.join(";", cells); // line
        })
        .collect(Collectors.toList());

    lines.addAll(content);

    return String.join("\n", lines);
  }
}
