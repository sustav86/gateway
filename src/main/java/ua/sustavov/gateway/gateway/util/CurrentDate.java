package ua.sustavov.gateway.gateway.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentDate {

    private static LocalDate now = LocalDate.now();

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        return now.format(formatter);
    }
}
