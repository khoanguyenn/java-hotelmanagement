package fraus.javaproject.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    /**
     * @param date the date to be returned as string
     * @return formatted string
     * */
    public static String format(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    /**
     * @param dateString the date as String
     * @return date object if not null
     * */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch(DateTimeException exception) {
            return null;
        }
    }

    /**
     * @param dateString the date as String
     * @return true if dateString is valid, false if dateString is not valid
     * */
    public static boolean isValid(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
