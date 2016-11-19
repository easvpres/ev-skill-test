package org.eas.model;

import java.time.LocalDate;

/**
 * @author eas
 */
public class Person {
    private String name;
    private LocalDate birthday;

    public Person() {
    }

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
