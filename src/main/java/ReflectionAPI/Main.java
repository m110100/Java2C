package ReflectionAPI;

import ReflectionAPI.Entities.Person;

public class Main {
    public static void main(String[] args) {
        TableBuilder.buildTable(Person.class);

        Session.persist(new Person(1, "Tony", Person.Gender.Male));
        Session.persist(new Person(2, "Alexa", Person.Gender.Female));
    }
}
