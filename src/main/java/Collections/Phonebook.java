package Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private Map<Long, String> phonebook;

    public Phonebook() {
        phonebook = new HashMap<>();
    }

    public void add(long phone_number, String surname) {
        phonebook.put(phone_number, surname);
    }

    public void get(String surname) {
        if (phonebook.containsValue(surname)) {
            Set<Map.Entry<Long, String>> set = phonebook.entrySet();

            for (Map.Entry<Long, String> temp : set) {
                if (temp.getValue().equals(surname)) {
                    System.out.printf("%s's phone numbers : %d%n", temp.getValue(), temp.getKey());
                }
            }
        }
        else System.out.printf("Surname %s is not on the list", surname);
    }
}
