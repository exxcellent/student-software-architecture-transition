package de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic;

import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types.DependencyFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public class FileParser {

  public String SEPARATOR = "/";

  public List<DependencyFile> parseFile(Path inputFile, String basePackage, List<String> componentBasePackages, boolean debugLogs)
      throws IOException, SAXException, ParserConfigurationException {
    List<DependencyFile> files = parseToXml(inputFile, debugLogs);

    checkFileSeparator(files);
    print("SEPARATOR: " + SEPARATOR, debugLogs);

    files = filterJavaFiles(files);
    files = filterJDKFiles(files);
    files = removePathPrefix(files);
    files = definePackagesAndClassName(files);

    files = defineDomain(files, basePackage);
    files = defineComponent(files, componentBasePackages);

    return files;
  }

  private List<DependencyFile> filterJavaFiles(List<DependencyFile> files) {
    return files.stream()
        .filter(DependencyFile::isJavaFile)
        .map(file -> {
          var filteredDependencies = file.getDependencies().stream()
              .filter(DependencyFile::isJavaFile)
              .collect(Collectors.toList());
          return new DependencyFile(file.getPath(), filteredDependencies);
        })
        .collect(Collectors.toList());
  }


  private List<DependencyFile> defineComponent(List<DependencyFile> files, List<String> componentBasePackages) {
    return files.stream()
        .peek(file -> {
          var dependencies = file.getDependencies().stream()
              .peek(dependency -> {
                componentBasePackages.forEach(componentBasePackage -> {
                  if (dependency.getPackage().contains(componentBasePackage)) {
                  var base = componentBasePackage + ".";
                    var packageWithoutBase = file.getPackage().replace(base, "");
                    var component = packageWithoutBase.contains(".") ? packageWithoutBase.split("\\.")[0] : packageWithoutBase;
                    dependency.setComponent(component);
                  }
                });
              })
              .collect(Collectors.toList());
          file.setDependencies(dependencies);
        })
        .peek(file -> {
          componentBasePackages.forEach(componentBasePackage -> {
            if (file.getPackage().contains(componentBasePackage)) {
              var base = componentBasePackage + ".";
              var packageWithoutBase = file.getPackage().replace(base, "");
              var component = packageWithoutBase.contains(".") ? packageWithoutBase.split("\\.")[0] : packageWithoutBase;
              file.setComponent(component);
            }
          });
        })
        .collect(Collectors.toList());
  }

  private List<DependencyFile> defineDomain(List<DependencyFile> files, String basePackage) {
    var base = basePackage + ".";

    return files.stream()
        .peek(file -> {
          var dependencies = file.getDependencies().stream()
              .peek(dependency -> {
                var packageWithoutBase = dependency.getPackage().replace(base, "");
                var domain = packageWithoutBase.contains(".") ? packageWithoutBase.split("\\.")[0] : packageWithoutBase;
                dependency.setDomain(domain);
              })
              .collect(Collectors.toList());
          file.setDependencies(dependencies);
        })
        .peek(file -> {
          var packageWithoutBase = file.getPackage().replace(base, "");
          var domain = packageWithoutBase.contains(".") ? packageWithoutBase.split("\\.")[0] : packageWithoutBase;
          file.setDomain(domain);
        })
        .collect(Collectors.toList());
  }

  private void checkFileSeparator(List<DependencyFile> files) {
    if (!files.get(0).getPath().contains(SEPARATOR)) {
      SEPARATOR = "\\"; // toggle separator
    }
  }

  private List<DependencyFile> definePackagesAndClassName(List<DependencyFile> files) {

    return files.stream()
        .map(file -> {
          var filteredDependencies = file.getDependencies().stream()
              .map(dependency -> {
                var newDependency = new DependencyFile(dependency.getPath());
                var dependencyFile = new File(dependency.getPath());
                newDependency.setFileName(dependencyFile.getName());
                newDependency.setClassName(dependencyFile.getName().replace(".java", ""));
                newDependency.setModule(dependency.getPath().split(SEPARATOR)[0]);

                newDependency.setPackage(toPackage(newDependency));

                return newDependency;
              })
              .collect(Collectors.toList());

          var newFile = new DependencyFile(file.getPath(), filteredDependencies);
          var dependencyFile = new File(file.getPath());
          newFile.setFileName(dependencyFile.getName());
          newFile.setClassName(dependencyFile.getName().replace(".java", ""));
          newFile.setModule(file.getPath().split(SEPARATOR)[0]);
          newFile.setPackage(toPackage(newFile));

          return newFile;
        })
        .collect(Collectors.toList());
  }

  private String toPackage(DependencyFile file) {
    return file.getPath()
        .replace(file.getModule() + SEPARATOR, "")
        .replace(SEPARATOR+file.getFileName(), "")
        .replace("src"+SEPARATOR+"main", "")
        .replace(SEPARATOR+"java"+SEPARATOR, "")
        .replace(SEPARATOR, ".");
  }

  private List<DependencyFile> removePathPrefix(List<DependencyFile> files) {
    var replace = "$PROJECT_DIR$/02-server/";
    return files.stream()
        .map(file -> {
          var filteredDependencies = file.getDependencies().stream()
              .peek(dependency -> dependency.setPath(dependency.getPath()
                  .replace(replace, "")))
              .collect(Collectors.toList());
          return new DependencyFile(file.getPath()
              .replace(replace, ""), filteredDependencies);
        })
        .collect(Collectors.toList());
  }

  private List<DependencyFile> filterJDKFiles(List<DependencyFile> files) {
    return files.stream()
        .filter(file -> !file.getPath().contains("java.base"))
        .map(file -> {
          var filteredDependencies = file.getDependencies().stream()
              .filter(dependency -> !dependency.getPath().contains("java.base"))
              .collect(Collectors.toList());
          return new DependencyFile(file.getPath(), filteredDependencies);
        })
        .collect(Collectors.toList());
  }

  private List<DependencyFile> parseToXml(Path inputFile, boolean debugLogs) throws ParserConfigurationException, SAXException, IOException {
    print("Parsing file...", debugLogs);
    print("File contents: ", debugLogs);

    List<DependencyFile> files = new ArrayList<>();

    Document doc = parseFileToXmlDocument(inputFile);
    NodeList fileElements = doc.getElementsByTagName("file");
    for (int temp = 0; temp < fileElements.getLength(); temp++) {
      Node fileNode = fileElements.item(temp);
      var filePath = fileNode.getAttributes().getNamedItem("path").getTextContent();
      var file = new DependencyFile(filePath);

      print("File: " + file.getPath(), debugLogs);

      if (fileNode.hasChildNodes()) {
        var dependencyElements = fileNode.getChildNodes();
        for (int j = 0; j < dependencyElements.getLength(); j++) {
          Node dependencyNode = dependencyElements.item(j);
          if (dependencyNode.hasAttributes()) {
            var dependencyPath = dependencyNode.getAttributes().getNamedItem("path").getTextContent();
            var dependency = new DependencyFile(dependencyPath);

            print2("Dependency: " + dependency.getPath(), debugLogs);

            file.addDependency(dependency);
          }
        }
      }

      files.add(file);
    }

    printSeparator();

    return files;
  }

  private Document parseFileToXmlDocument(Path inputFile) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(inputFile.toFile());
    doc.getDocumentElement().normalize();
    return doc;
  }

  private String readFile(Path inputFile) throws IOException {
    print("Reading file...");
    byte[] bytes = Files.readAllBytes(inputFile);
    return new String(bytes);
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
}
