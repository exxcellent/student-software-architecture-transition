package de.exxcellent.student.softwarearchitecture.evaluation.words;

import de.exxcellent.student.softwarearchitecture.evaluation.words.logic.CSVConverter;
import de.exxcellent.student.softwarearchitecture.evaluation.words.logic.FileParser;
import de.exxcellent.student.softwarearchitecture.evaluation.words.types.Word;
import org.apache.commons.cli.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class WordCount {

    public static void main( String[] args ) throws ParseException, IOException, ParserConfigurationException, SAXException {
        Options options = new Options();
        options.addOption("source", true, "source file");
        options.addOption("destination", true, "destination csv file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        new WordCount().run(cmd);
    }

    public void run(CommandLine cmd) throws IOException, ParserConfigurationException, SAXException {
        // info
       printSeparator();
       print("Starting WORD COUNTER");
       Path inputFile = Paths.get(cmd.getOptionValue("source"));
       Path destinationFile = Paths.get(cmd.getOptionValue("destination"));
       print("Analysing file: " + cmd.getOptionValue("source") );
       print("Output file: " + cmd.getOptionValue("destination") );
       print("File exists: " + inputFile.toFile().exists());
       printSeparator();

       // parsing
        List<Word> words = new FileParser().parseFile(inputFile, false);

        // print modules, packages, classes and dependencies
        words.forEach(word -> {
          print(word.getWord() + ": " + word.getCount());
        });

        printSeparator();

        print("Number of words: " + words.size());

        var csvContent = new CSVConverter().convertToCSV(words);

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
