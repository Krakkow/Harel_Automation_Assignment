package com.harel.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class LogicFuncs {

    private LogicFuncs() {
    }

    public static boolean isTitleValid(String title) {
        return title != null && !title.trim().isEmpty();
    }

    public static boolean isUrlValid(String url, int year) {
        return url != null && url.startsWith("https") && url.contains(String.valueOf(year));
    }

    public static boolean isDateFormattedCorrectly(LocalDate date, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String formatted = date.format(formatter);
            LocalDate parsed = LocalDate.parse(formatted, formatter);
            return parsed.equals(date);
        } catch (Exception e) {
            return false;
        }
    }

}
