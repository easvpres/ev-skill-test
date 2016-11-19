package org.eas.service;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * @author eas
 */
public class DaysLeftCalculatorTest {

    private DaysLeftCalculator daysLeftCalculator = new DaysLeftCalculator();

    @Test
    public void test() throws Exception {
        LocalDate now = LocalDate.of(2016, 1, 1);
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        Assert.assertEquals(0, daysLeftCalculator.calculate(now, birthday));

        now = LocalDate.of(2016, 1, 1);
        birthday = LocalDate.of(2000, 1, 2);
        Assert.assertEquals(1, daysLeftCalculator.calculate(now, birthday));

        now = LocalDate.of(2016, 1, 2);
        birthday = LocalDate.of(2000, 1, 1);
        Assert.assertEquals(365, daysLeftCalculator.calculate(now, birthday));
    }
}