package uk.org.bsped.util;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Calendar;

public class CalendarHelper {

    public static int calculateDateDifference(Calendar start, Calendar end) {
        LocalDate startLocalDate = new LocalDate(start.getTimeInMillis());
        LocalDate endLocalDate = new LocalDate(end.getTimeInMillis());
        return Days.daysBetween(startLocalDate, endLocalDate).getDays();
    }
}
