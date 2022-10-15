package Collections;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Задание 1.
        List<String> words = Arrays.asList("Apple", "Apple", "Banana", "Orange", "Orange", "Peach", "Strawberry", "Strawberry", "Apricot", "Banana");

        findAndPrintUniqueWords(words);

        System.out.println("");

        // Задание 2. Телефонный справочник
        Phonebook phoneBook = new Phonebook();

        phoneBook.add(9115597636L, "Istomin");
        phoneBook.add(9116005643L, "Glukhih");
        phoneBook.add(9021089032L, "Istomin");

        phoneBook.get("Istomin");
        phoneBook.get("Glukhih");
    }

    private static void findAndPrintUniqueWords(List<String> array) {
        int count;

        Set<String> tempArray = new LinkedHashSet<>(array);

        System.out.println(tempArray + "\n");

        for (String s : tempArray) {
            count = 0;

            for (String v : array) if (s.equals(v)) count++;

            System.out.printf("%s was repeated in the list : %d%n", s, count);
        }
    }
}
