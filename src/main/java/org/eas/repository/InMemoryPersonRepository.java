package org.eas.repository;

import org.eas.model.Person;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author eas
 */
@Repository
public class InMemoryPersonRepository implements PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryPersonRepository.class);

    private List<Person> persons = new CopyOnWriteArrayList<>();

    @Override
    public void save(Person person) {
        logger.info("save(person={})", person);
        persons.add(person);
    }

    @Override
    public List<Person> findByMonth(int month) {
        logger.info("findByMonth(month={})", month);
        return persons.stream().filter(person -> month == new LocalDateTime(person.getBirthday()).getMonthOfYear()).collect(Collectors.toList());
    }

}
