package org.jpmc.forex.trade.util;

import org.jpm.trade.report.util.DateConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class DateStringConverterTest {

    @Test (expected = DateTimeParseException.class)
    public void testConvertStringToDateException() throws DateTimeParseException {
       DateConverter.convertStringToDate("2017-32-03");
    }

    @Test
    public void testConvertStringToDate() throws DateTimeParseException {
        Assert.assertNotNull(DateConverter.convertStringToDate("2017-02-03"));
        Assert.assertEquals(DayOfWeek.FRIDAY, DateConverter.convertStringToDate("2017-02-03").getDayOfWeek());
        Assert.assertEquals(Month.FEBRUARY, DateConverter.convertStringToDate("2017-02-03").getMonth());
        Assert.assertEquals(2017, DateConverter.convertStringToDate("2017-02-03").getYear());
    }

    @Test
    public void testConvertDateToString() {
        Assert.assertNotNull(DateConverter.convertDateToString(LocalDate.of(2017,03,13)));
        Assert.assertEquals("2017-03-13", DateConverter.convertDateToString(LocalDate.of(2017,03,13)));
    }
}
