package org.eas.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author eas
 */
@Component
public class DaysLeftCalculator {

    public int calculate(LocalDate date, LocalDate birthday) {
        LocalDate nextBirthday = birthday.withYear(date.getYear());
        if (nextBirthday.isBefore(date)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return (int) ChronoUnit.DAYS.between(date, nextBirthday);
    }
}
