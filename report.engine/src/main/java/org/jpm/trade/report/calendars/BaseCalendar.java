package org.jpm.trade.report.calendars;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class BaseCalendar {

    /**
     * Method to get valid businessDate for an instruction's settlement date
     * @param settlementDate
     * @return
     */
    public LocalDate getBusinessDay(LocalDate settlementDate) {
        while(isHoliday(settlementDate)) {
            settlementDate = settlementDate.plus(1, ChronoUnit.DAYS);
        }
        return settlementDate;
    }

    public abstract boolean isHoliday(LocalDate date);
}
