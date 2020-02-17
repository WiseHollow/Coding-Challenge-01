package genova.codingchallenge;

import genova.codingchallenge.person.Person;
import genova.codingchallenge.person.PersonListAnalyser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonListAnalyserTest {
    private List<Person> personList;

    @Before
    public void setup() {
        personList = new ArrayList<>();
        personList.add(new Person("John", "Brooks", 63));
        personList.add(new Person("Adam", "Lewis", 32));
        personList.add(new Person("Jon", "Smith", 15));
        personList.add(new Person("Adam", "Brady", 21));
        personList.add(new Person("Sam", "Pond", 24));
    }

    @Test
    public void testDuplicateFirstNameCount() {
        PersonListAnalyser analyser = new PersonListAnalyser(personList);
        assert (analyser.duplicateFirstNames() == 1);
    }

    @Test
    public void testAverageAge() {
        PersonListAnalyser analyser = new PersonListAnalyser(personList);
        assert (analyser.averageAge() == 31);
    }
}
