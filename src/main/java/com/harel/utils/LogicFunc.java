package com.harel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogicFunc {

    private LogicFunc() {
    }

    // Default formatter used by the UI input fields
    private static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Returns today's date.
     */
    public static LocalDate getTodayDate() {
        return LocalDate.now();
    }

    /**
     * Returns a date X days from today.
     *
     * @param daysToAdd number of days to add
     */
    public static LocalDate getDateFromToday(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd);
    }

    /**
     * Formats a LocalDate to dd/MM/yyyy (UI input format).
     */
    public static String formatDateForInput(LocalDate date) {
        return date.format(DATE_INPUT_FORMATTER);
    }

    /**
     * Convenience method:
     * returns today + X days already formatted for input.
     */
    public static String getFormattedDateFromToday(int daysToAdd) {
        LocalDate date = getDateFromToday(daysToAdd);
        return formatDateForInput(date);
    }

    // Formatter for the date widget (used in the date picker)
    private static final DateTimeFormatter DATE_WIDGET_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Adds days to a given date
    public static LocalDate addDays(LocalDate date, int daysToAdd) {
        return date.plusDays(daysToAdd);
    }

    // Formats a LocalDate to yyyy-MM-dd (date widget format)
    public static String formatDateForWidget(LocalDate date) {
        return date.format(DATE_WIDGET_FORMATTER);
    }
}
