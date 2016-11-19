package org.eas.service;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;

/**
 * @author eas
 */
@Component
public class DaysLeftCalculator {

    public int calculate(DateTime now, DateTime birthday) {
        DateTime nextBirthday = birthday.withYear(now.getYear());
        if (nextBirthday.isBefore(now)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return Days.daysBetween(now, nextBirthday).getDays();
    }
}
