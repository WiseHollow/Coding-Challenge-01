package genova.codingchallenge.person;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class PersonListAnalyser {
    private static final String DUPLICATE_FIRST_NAMES = "There were %d incidences of duplicate first names";
    private static final String AVERAGE_AGE = "The average age for the provided data was %a";
    private static final SimpleDateFormat DATE_STAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    private List<Person> personList;
    private Logger logger;

    /**
     * Constructor for PersonListAnalyser.
     * @param personList The Person list to prepare for analysing
     */
    public PersonListAnalyser(List<Person> personList) {
        this.personList = personList;
        this.logger = Logger.getLogger(getClass().getSimpleName());
    }

    /**
     * Get the average age of all read Person objects.
     * @return Average age of Person objects
     */
    public long averageAge() {
        return Math.round(personList.stream().mapToInt(Person::getAge).average().orElse(0d));
    }

    /**
     * Get the amount of times a first name was found accounted for more than once.
     * @return Duplicate first names
     */
    public int duplicateFirstNames() {
        Map<String, Integer> firstNameCount = firstNameCount();
        return firstNameCount.values().stream().mapToInt(Integer::intValue).sum() - firstNameCount.size();
    }

    /**
     * Print a short analysis of the data to console.
     */
    public void printAnalysis() {
        Collections.sort(personList);
        System.out.println("-- Analysis of data --");
        System.out.println();
        System.out.println("Lastname, firstname, age");
        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println();
        System.out.println(DUPLICATE_FIRST_NAMES.replace("%d", String.valueOf(duplicateFirstNames())));
        System.out.println(AVERAGE_AGE.replace("%a", String.valueOf(averageAge())));
    }

    /**
     * Print a short analysis of the data to a CSV file with the format 'analysis-<date-stamp>.csv'.
     * If the file already exists, it will not be written to.
     * If the file does not exist, it will be created.
     */
    public void recordCsv() {
        Collections.sort(personList);
        File file = new File("analysis-" + DATE_STAMP_FORMAT.format(new Date()) + ".csv");
        if (prepareFile(file)) {
            CSVWriter csvWriter = null;
            try {
                csvWriter = new CSVWriter(new FileWriter(file));
                String[] header = { "Last Name", "First Name", "Age" };
                csvWriter.writeNext(header);

                for (Person person : personList) {
                    csvWriter.writeNext(person.toCsvRow());
                }
                csvWriter.writeNext(new String[] {});
                csvWriter.writeNext(new String[] { DUPLICATE_FIRST_NAMES.replace("%d", String.valueOf(duplicateFirstNames())) });
                csvWriter.writeNext(new String[] { AVERAGE_AGE.replace("%a", String.valueOf(averageAge())) });
            } catch (IOException exception) {
                logger.severe("There was a problem performing IO actions on file: " + file.getAbsolutePath());
                exception.printStackTrace();
            } finally {
                closeCsvWriter(csvWriter);
            }
        }
    }


    /**
     * Creates file if it does not exist.
     * @return True/False whether the was created
     */
    private boolean prepareFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.severe("Could not create file: " + file.getAbsolutePath());
                e.printStackTrace();
                return false;
            }
        } else {
            logger.severe("File already exists, will not write to: " + file.getAbsolutePath());
            return false;
        }

        return true;
    }

    private Map<String, Integer> firstNameCount() {
        Map<String, Integer> countMap = new HashMap<>();
        personList.forEach(person -> countMap.put(person.getFirstName(), countMap.getOrDefault(person.getFirstName(), 0) + 1));
        return countMap;
    }

    private void closeCsvWriter(CSVWriter csvWriter) {
        if (csvWriter != null) {
            try {
                csvWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
