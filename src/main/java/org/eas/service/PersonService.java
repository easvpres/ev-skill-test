package org.eas.service;

import org.eas.dto.DaysToBirthdayLeft;
import org.eas.model.Person;
import org.eas.repository.PersonRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eas
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DaysLeftCalculator daysLeftCalculator;

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<DaysToBirthdayLeft> getDaysToBirthdayLeft(int month) {
        DateTime now = new DateTime();
        return personRepository.findByMonth(month).stream().map(person -> {
            DaysToBirthdayLeft result = new DaysToBirthdayLeft();
            result.setPersonnName(person.getName());
            result.setDaysLeft(daysLeftCalculator.calculate(now, new DateTime(person.getBirthday())));
            return result;
        }).collect(Collectors.toList());
    }
}
