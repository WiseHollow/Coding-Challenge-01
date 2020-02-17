package genova.codingchallenge.person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonListReader {
    private String[] filePaths;
    private Logger logger;
    private int errors = -1;

    /**
     * Constructor for PersonListReader.
     * @param filePaths Paths of files to read
     */
    public PersonListReader(String... filePaths) {
        this.filePaths = filePaths;
        this.logger = Logger.getLogger(getClass().getSimpleName());
    }

    /**
     * Reads each file and parses them into a list of Person objects.
     * Where any error occurs while reading, the entry will be skipped,
     * an error counter will be incremented, and an error will be
     * printed to console.
     * @return List of Person objects
     */
    public List<Person> read() {
        errors = 0;
        List<Person> personList = new ArrayList<>();
        for (String filePath : filePaths) {
            personList.addAll(read(filePath));
        }
        return personList;
    }

    /**
     * Gets the number of errors on the last read. Will return '-1' if
     * there was no previous read.
     * @return Number of errors from previous read
     */
    public int getErrors() {
        return errors;
    }

    private List<Person> read(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            errors++;
            logger.severe("File path provided is invalid: " + filePath);
            return Collections.emptyList();
        }

        Stream<String> lines;
        try {
            lines = Files.lines(file.toPath());
        } catch (IOException e) {
            errors++;
            logger.severe("Exception thrown while attempting to read file: " + filePath);
            e.printStackTrace();
            return Collections.emptyList();
        }
        return lines
                .skip(1)
                .filter(line -> line.length() > 0)
                .map(this::fromFileLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Person fromFileLine(String line) {
        String[] personElements = line.split("\t");
        if (personElements.length < 3) {
            errors++;
            logger.warning("Each list entry can only have 3 tab-delimited items: " + line);
            return null;
        }

        int age;
        try {
            age = Integer.parseInt(personElements[2]);
        } catch (NumberFormatException exception) {
            errors++;
            logger.warning("Column 3 (age) must be an integer: " + line);
            return null;
        }

        String firstName = personElements[0];
        String lastName = personElements[1];

        return new Person(firstName, lastName, age);
    }
}
