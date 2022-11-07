package StreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<Employee> employee_list = new ArrayList<>(Arrays.asList(
            new Employee("James", 21, 3000),
            new Employee("William", 43, 5000),
            new Employee("Tony", 30, 4000),
            new Employee("Jade", 25, 3200)
    ));

    static int n = 3;

    public static void main(String[] args) {
        /*
        1.Создайте  массив  с  набором  слов,  и  с  помощью  Stream  API  найдите  наиболее  часто встречающееся;
        2.Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите среднюю зарплату сотрудника;
        3.Напишите метод, способный найти в массиве сотрудников из п. 2 найдите N самых старших сотрудников и отпечатает в консоль
        сообщение вида “N самых старших сотрудников зовут: имя1, имя2, имяN;”.
        */
        taskOne();
        taskTwo();
        taskThree();
    }
    public static void taskOne() {
        String[] word_list = new String[]{ "Banana", "Banana", "Banana", "Banana", "Orange", "Orange", "Orange",
                "Orange", "Strawberry", "Apple", "Apple", "Apple", "Apple" };

//        common_word = word_list
//                .stream()
//                    .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
//                    .entrySet()
//                    .stream()
//                        .max(Map.Entry.comparingByValue()).orElseThrow().getKey();

        // key - name of fruit, value - count of fruit
        Arrays.stream(word_list)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .forEach((key, value) -> {
                    if (Objects.equals(value, Arrays.stream(word_list)
                            .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                            .entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getValue()))

                        System.out.printf("%s ", key);
                });

        System.out.println();

    }

    public static void taskTwo() {
        double average_salary;

        average_salary = employee_list.stream().mapToDouble(Employee::getSalary).average().orElseThrow();

        System.out.printf("Average employee salary is %f%n", average_salary);
    }

    public static void taskThree() {
        System.out.println(
                employee_list.stream()
                        .sorted((o1, o2) -> o2.getAge() - o1.getAge()).limit(n).map(Employee::getName)
                        .collect(Collectors.joining(", ", "Eldest employees names: ", "."))
        );
    }
}
