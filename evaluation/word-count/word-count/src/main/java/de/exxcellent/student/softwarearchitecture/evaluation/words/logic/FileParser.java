package de.exxcellent.student.softwarearchitecture.evaluation.words.logic;

import de.exxcellent.student.softwarearchitecture.evaluation.words.types.Word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class FileParser {

  private List<String> JAVA_KEYWORDS = List.of("abstract", "continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float", "native", "super", "while",
      "Long", "String", "Boolean", "Integer", "Function", "Thread", "toInstant", "OffsetDateTime", "HttpEntity", "LocalTime", "ZoneOffset",
      "var", "stream", "in", "Supplier", "Target", "List", "null", "Map", "ArrayList", "isPresent", "asList", "containsAll", "toByteArray", "flatMap", "map", "filter",
      "collect", "toSet", "sleep", "packages", "contains", "Set", "Collections", "Collectors", "LocalDate", "Float", "Duration", "Object");

  private Pattern NUMBERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
  private Pattern LONG_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?L");

  private List<String> BLACK_LIST = List.of("run", "saveAndFlush", "containsKey", "getProperty", "atOffset", "mkdirs", "set", "ManyToOne", "Version",
      "filter", "csrf", "mvc", "Math", "Class", "Filter", "Method", "Unirest", "ActiveProfiles", "RequestParam", "Param", "Assertions", "ObjectMapper",
      "IdClass", "Documented", "FilterRegistrationConfiguration", "Entry", "Preconditions", "Autowired", "MediaType", "HTTPStatus", "Logger", "PathVariable",
      "Permission", "RequestMapping", "Column");

  public List<Word> parseFile(Path inputFile, boolean debugLogs)
      throws IOException {
    List<Word> words = parseTSV(inputFile, debugLogs);

    words = filterSpecialCharacters(words, debugLogs);
    words = filterJavaKeyWords(words, debugLogs);
    words = filterNumbers(words, debugLogs);
    words = filterPackages(words, debugLogs);
    words = filterUpperCase(words, debugLogs);
    words = filterBlackList(words, debugLogs);
    words = filterExceptions(words, debugLogs);
    words = filterWordsWithStartingLowLetter(words, debugLogs);

    return words;
  }



  private List<Word> filterWordsWithStartingLowLetter(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !Character.isLowerCase(word.getWord().toCharArray()[0]))
        .collect(Collectors.toList());

  }

  private List<Word> filterExceptions(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !word.getWord().contains("Exception"))
        .collect(Collectors.toList());

  }

  private List<Word> filterBlackList(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !BLACK_LIST.contains(word.getWord()))
        .collect(Collectors.toList());

  }

  private List<Word> filterUpperCase(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !isStringUpperCase(word.getWord()))
        .collect(Collectors.toList());
  }

  private List<Word> filterPackages(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !word.getWord().contains("de"))
        .filter(word -> !word.getWord().contains("org"))
        .filter(word -> !word.getWord().contains("com"))
        .filter(word -> !word.getWord().contains("exxcellent"))
        .filter(word -> !word.getWord().contains("softwarearchitecture"))
        .filter(word -> !word.getWord().contains("student"))
        .filter(word -> !word.getWord().contains("springframework"))
        .filter(word -> !word.getWord().contains("java"))
        .filter(word -> !word.getWord().contains("nio"))
        .filter(word -> !word.getWord().contains("orm"))
        .collect(Collectors.toList());

  }

  private List<Word> filterNumbers(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !NUMBERIC_PATTERN.matcher(word.getWord()).matches())
        .filter(word -> !LONG_PATTERN.matcher(word.getWord()).matches())
        .collect(Collectors.toList());

  }

  private List<Word> filterJavaKeyWords(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !JAVA_KEYWORDS.contains(word.getWord()))
        .collect(Collectors.toList());

  }

  private List<Word> filterSpecialCharacters(List<Word> words, boolean debugLogs) {
    return words.stream()
        .filter(word -> !word.getWord().contains("\""))
        .filter(word -> !word.getWord().contains("+"))
        .filter(word -> !word.getWord().contains("{"))
        .filter(word -> !word.getWord().contains(">"))
        .filter(word -> !word.getWord().contains("<"))
        .filter(word -> !word.getWord().contains("="))
        .filter(word -> !word.getWord().contains("!"))
        .filter(word -> !word.getWord().contains("|"))
        .filter(word -> !word.getWord().contains("..."))
        .filter(word -> !word.getWord().contains("::"))
        .collect(Collectors.toList());

  }

  private List<Word> parseTSV(Path inputFile, boolean debugLogs) throws IOException {
    var fileLines = readingFile(inputFile, debugLogs);

    List<Word> words = new ArrayList<>();

    fileLines.forEach(line -> {
      var cells = line.split("\\t");
      words.add(new Word(cells[0], Long.parseLong(cells[1])));
    });

    return words;
  }

  private List<String> readingFile(Path inputFile, boolean debugLogs) throws IOException {
    print("Reading file...", debugLogs);
    return Files.readAllLines(inputFile);
  }



  private void print(String value) {
    System.out.println( "   " + value);
  }

  private void print2(String value) {
    System.out.println( "      " + value);
  }

  private void print(String value, boolean printLogs) {
    if (printLogs) System.out.println( "   " + value);
  }

  private void print2(String value, boolean printLogs) {
    if (printLogs) System.out.println( "      " + value);
  }

  private void printSeparator() {
    System.out.println( "----------------------------------------------------" );
  }

  private boolean isStringUpperCase(String str){

    //convert String to char array
    char[] charArray = str.toCharArray();

    for (char c : charArray) {
      if (!Character.isUpperCase(c) && c != '_')
        return false;
    }

    return true;
  }
}
