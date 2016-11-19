package org.eas.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author eas
 */
public class DaysLeftCalculatorTest {

    private DaysLeftCalculator daysLeftCalculator = new DaysLeftCalculator();

    @Test
    public void test() throws Exception {
        DateTime now = new DateTime(2016, 1, 1, 0, 0, 0);
        DateTime birthday = new DateTime(2000, 1, 1, 0, 0, 0);
        Assert.assertEquals(0, daysLeftCalculator.calculate(now, birthday));

        now = new DateTime(2016, 1, 1, 0, 0, 0);
        birthday = new DateTime(2000, 1, 2, 0, 0, 0);
        Assert.assertEquals(1, daysLeftCalculator.calculate(now, birthday));

        now = new DateTime(2016, 1, 2, 0, 0, 0);
        birthday = new DateTime(2000, 1, 1, 0, 0, 0);
        Assert.assertEquals(365, daysLeftCalculator.calculate(now, birthday));
    }
}