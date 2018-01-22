package org.jpm.trade.report.calendars;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SunToThursCalendar extends BaseCalendar {

    /**
     * Method to check holidays based on calendar
     */
    @Override
    public boolean isHoliday(LocalDate currentDay) {
        return (DayOfWeek.FRIDAY.name().equals(currentDay.getDayOfWeek().name())
                || DayOfWeek.SATURDAY.name().equals(currentDay.getDayOfWeek().name()));
    }
}
