package StreamAPI;

public class Employee {
    private String name;
    private int age;
    private int salary;

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() { return salary; }

    public String getName() { return name; }

    public int getAge() { return age; }
}
