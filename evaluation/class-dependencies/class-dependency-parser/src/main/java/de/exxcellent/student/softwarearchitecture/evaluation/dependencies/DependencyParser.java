package de.exxcellent.student.softwarearchitecture.evaluation.dependencies;

import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic.Aggregator;
import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic.CSVConverter;
import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.logic.FileParser;
import de.exxcellent.student.softwarearchitecture.evaluation.dependencies.types.DependencyFile;
import org.apache.commons.cli.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;


public class DependencyParser {

    private String BASE_PACKAGE = "de.exxcellent.student.softwarearchitecture.transition";
    private List<String> COMPONENT_BASE_PACKAGES = List.of(
        "de.exxcellent.student.softwarearchitecture.transition.navigation",
        "de.exxcellent.student.softwarearchitecture.transition.planning"
    );

    public static void main( String[] args ) throws ParseException, IOException, ParserConfigurationException, SAXException {
        Options options = new Options();
        options.addOption("source", true, "source xml file");
        options.addOption("destination", true, "destination csv file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        new DependencyParser().run(cmd);
    }

    public void run(CommandLine cmd) throws IOException, ParserConfigurationException, SAXException {
        // info
       printSeparator();
       print("Starting CLASS DEPENDENCY PARSER");
       Path inputFile = Paths.get(cmd.getOptionValue("source"));
       Path destinationFile = Paths.get(cmd.getOptionValue("destination"));
       print("Analysing file: " + cmd.getOptionValue("source") );
       print("Output file: " + cmd.getOptionValue("destination") );
       print("File exists: " + inputFile.toFile().exists());
       print("Using base package: " + BASE_PACKAGE);
       print("Search components in: " + String.join(", ", COMPONENT_BASE_PACKAGES));
       printSeparator();

       // parsing
        List<DependencyFile> files = new FileParser()
            .parseFile(inputFile, BASE_PACKAGE, COMPONENT_BASE_PACKAGES, false);

        files.sort(Comparator.comparing(DependencyFile::getModule));

        // print modules, packages, classes and dependencies
        files.forEach(file -> {
            if (file.isJavaFile()) {
                print(file.getModule() + " :: " + file.getDomain() + " > " + file.getComponent() + " >> " + file.getPackage() + " Class: " + file.getClassName());

                file.getDependencies().forEach(dependency -> {
                    if (dependency.isJavaFile()) {
                        print2("=> " + dependency.getModule() + " :: " + dependency.getDomain() + " > " + dependency.getComponent() + " >> " + dependency.getPackage() + " Class: " + dependency.getClassName());
                    }
                });
            }
        });

        printSeparator();

        // aggregate

        var filesWithStatistics = new Aggregator().aggregateFiles(files);

        filesWithStatistics.forEach(file -> {
            print(file.getFile().getClassName() + ": NumberOfDependenciesInDifferentModule = " + file.getNumberOfDependenciesInDifferentModule());
        });


        var csvContent = new CSVConverter().convertToCSV(filesWithStatistics);

        Files.writeString(destinationFile, csvContent, StandardCharsets.UTF_8);
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
