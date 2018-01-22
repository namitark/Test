package org.jpmc.forex.trade.calendars;

import org.jpm.trade.report.calendars.MonToFridayCalendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class MonToFridayCalendarTest {

    MonToFridayCalendar monToFridayCalendar;
    LocalDate sundayDate;
    LocalDate mondayDate;
    LocalDate saturdayDate;
    LocalDate fridayDate;

    @Before
    public void setUp() {
        monToFridayCalendar = new MonToFridayCalendar();
        sundayDate = LocalDate.of(2017,9,03);
        mondayDate = LocalDate.of(2017,9,04);
        saturdayDate = LocalDate.of(2017,9,02);
        fridayDate = LocalDate.of(2017,9,8);
    }

        @Test
    public void testIsHoliday() {
        Assert.assertEquals(Boolean.valueOf(true), monToFridayCalendar.isHoliday(sundayDate));
        Assert.assertEquals(Boolean.valueOf(true), monToFridayCalendar.isHoliday(saturdayDate));
        Assert.assertEquals(Boolean.valueOf(false), monToFridayCalendar.isHoliday(mondayDate));
        Assert.assertEquals(Boolean.valueOf(false), monToFridayCalendar.isHoliday(fridayDate));
    }

    @After
    public void tearDown() {
        monToFridayCalendar = null;
        sundayDate = null;
        mondayDate = null;
        saturdayDate = null;
        fridayDate = null;
    }
}
