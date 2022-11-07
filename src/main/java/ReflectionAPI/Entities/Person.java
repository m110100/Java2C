package ReflectionAPI.Entities;

import ReflectionAPI.Annotations.Column;
import ReflectionAPI.Annotations.Table;

@Table("Person")
public class Person {
    @Column(name = "id")
    private int id;
    @Column
    private String name;

    @Column
    private Enum<Gender> gender;

    public Person(int id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public enum Gender {
        Male, Female
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return (Gender) gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
