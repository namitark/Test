package org.jpmc.forex.trade.calendars;

import org.jpm.trade.report.calendars.SunToThursCalendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SunToThursCalendarTest {

    SunToThursCalendar sunToThursCalendar;
    LocalDate fridayDate;
    LocalDate mondayDate;
    LocalDate saturdayDate;
    LocalDate sundayDate;

    @Before
    public void setUp() {
        sunToThursCalendar = new SunToThursCalendar();
        sundayDate = LocalDate.of(2017,9,03);
        mondayDate = LocalDate.of(2017,9,04);
        saturdayDate = LocalDate.of(2017,9,02);
        fridayDate = LocalDate.of(2017,9,8);
    }

    @Test
    public void testIsHoliday() {
        Assert.assertEquals(Boolean.valueOf(false), sunToThursCalendar.isHoliday(sundayDate));
        Assert.assertEquals(Boolean.valueOf(true), sunToThursCalendar.isHoliday(saturdayDate));
        Assert.assertEquals(Boolean.valueOf(false), sunToThursCalendar.isHoliday(mondayDate));
        Assert.assertEquals(Boolean.valueOf(true), sunToThursCalendar.isHoliday(fridayDate));
    }

    @After
    public void tearDown() {
        sunToThursCalendar = null;
        sundayDate = null;
        mondayDate = null;
        saturdayDate = null;
        fridayDate = null;
    }
}
