package genova.codingchallenge;

import genova.codingchallenge.person.PersonListReader;
import org.junit.Test;

public class PersonListReaderTest {
    @Test
    public void testRequiresTabDelimiter() {
        PersonListReader reader = new PersonListReader("NameListTest-1.txt");
        reader.read();
        assert (reader.getErrors() == 1);
    }

    @Test
    public void testAgeMustBeInteger() {
        PersonListReader reader = new PersonListReader("NameListTest-2.txt");
        reader.read();
        assert (reader.getErrors() == 1);
    }

    @Test
    public void testRequiresThreeElements() {
        PersonListReader reader = new PersonListReader("NameListTest-3.txt");
        reader.read();
        assert (reader.getErrors() == 1);
    }
}
