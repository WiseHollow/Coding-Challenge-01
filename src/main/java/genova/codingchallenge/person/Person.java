package genova.codingchallenge.person;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    /**
     * Person constructor
     * @param firstName First name of person
     * @param lastName Last name of person
     * @param age Age of person
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Get first name of person.
     * @return First name
     */
    String getFirstName() {
        return firstName;
    }

    /**
     * Get last name of person.
     * @return Last name
     */
    String getLastName() {
        return lastName;
    }

    /**
     * Get age of person.
     * @return Age
     */
    int getAge() {
        return age;
    }

    /**
     * Create a string array holding the data in the order of last name, first name, and age.
     * @return String array with the desired data order when recording to CSV file
     */
    String[] toCsvRow() {
        return new String[] { lastName, firstName, String.valueOf(age)};
    }

    /**
     * Compare person last name to provided person object.
     * @param o Person to compare last name
     * @return Result of comparing last names.
     */
    @Override
    public int compareTo(Person o) {
        return lastName.compareTo(o.lastName);
    }

    /**
     * Gets string representation of object in the format 'last name first name age'.
     * @return String representation of person object
     */
    @Override
    public String toString() {
        return lastName + " " + firstName + " " + age;
    }
}
