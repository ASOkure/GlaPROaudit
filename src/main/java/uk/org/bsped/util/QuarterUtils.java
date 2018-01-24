package uk.org.bsped.util;

import lombok.extern.log4j.Log4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Log4j
public class QuarterUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isQuarterStart(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        return ((month == Calendar.JANUARY && date == 1) ||
                (month == Calendar.APRIL && date == 1) ||
                (month == Calendar.JULY && date == 1) ||
                (month == Calendar.OCTOBER && date == 1));
    }

    public static boolean isQuarterEnd(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        return ((month == 3 && date == 31) ||
                (month == 6 && date == 30) ||
                (month == 9 && date == 30) ||
                (month == 12 && date == 31));
    }

    public static Calendar getCurrentQuarterStartDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int quarterId = month / 3 + 1;
        switch (quarterId) {
            case 1:
                return new GregorianCalendar(year, Calendar.JANUARY, 1);
            case 2:
                return new GregorianCalendar(year, Calendar.APRIL, 1);
            case 3:
                return new GregorianCalendar(year, Calendar.JULY, 1);
            case 4:
                return new GregorianCalendar(year, Calendar.OCTOBER, 1);
        }
        return null;
    }

    public static String getCurrentQuarterName(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int quarterId = month / 3 + 1;
        return year + " Q" + quarterId;
    }

    public static Calendar getPreviousQuarterStartDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int quarterId = month / 3 + 1;
        switch (quarterId) {
            case 1:
                return new GregorianCalendar(year - 1, Calendar.OCTOBER, 1);
            case 2:
                return new GregorianCalendar(year, Calendar.JANUARY, 1);
            case 3:
                return new GregorianCalendar(year, Calendar.APRIL, 1);
            case 4:
                return new GregorianCalendar(year, Calendar.JULY, 1);
        }
        return null;
    }

    public static String getPreviousQuarterName(Calendar calendar) {
        Calendar previousQuarterStartDate = getPreviousQuarterStartDate(calendar);
        return getCurrentQuarterName(previousQuarterStartDate);
    }

//    public static void main(String[] args) {
//        Calendar calendar = new GregorianCalendar(2016, Calendar.MARCH, 32);
////        Calendar calendar = Calendar.getInstance();
//        log.info(calendar.get(Calendar.DATE));
//        log.info(calendar.get(Calendar.MONTH) + 1);
//        log.info("isQuarterEnd = " + isQuarterEnd(calendar));
//    }
}
