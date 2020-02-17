package genova.codingchallenge;

import genova.codingchallenge.person.Person;
import genova.codingchallenge.person.PersonListAnalyser;
import genova.codingchallenge.person.PersonListReader;

import java.util.List;

public class Main {
    private static final String[] DEFAULT_LIST_PATHS = { "NameList1.txt", "NameList2.txt" };

    public static void main(String[] args) {
        String[] lists = args.length > 0 ? args : DEFAULT_LIST_PATHS;
        PersonListReader personListReader = new PersonListReader(lists);
        List<Person> personList = personListReader.read();
        PersonListAnalyser analyser = new PersonListAnalyser(personList);

        analyser.printAnalysis();
        analyser.recordCsv();
    }


}
