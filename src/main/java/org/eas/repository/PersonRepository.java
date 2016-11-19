package org.eas.repository;

import org.eas.model.Person;

import java.util.List;

/**
 * @author eas
 */
public interface PersonRepository {
    void save(Person person);

    List<Person> findByMonth(int month);
}
