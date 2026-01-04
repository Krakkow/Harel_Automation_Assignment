package com.pingidentity.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import com.pingidentity.POJOs.NewsItem;

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

    public static void sortNewsItems(List<NewsItem> items) {
        items.sort(Comparator
                .comparing(NewsItem::getPublishedDate)
                .thenComparing(NewsItem::getNewsType));
    }

    public static void exportNewsToFile(List<NewsItem> newsItems, String fileName) {
        newsItems.sort(Comparator
                .comparing(NewsItem::getPublishedDate)
                .thenComparing(NewsItem::getNewsType, String.CASE_INSENSITIVE_ORDER));

        File targetDir = new File("target");
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File file = new File(targetDir, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (NewsItem item : newsItems) {
                writer.write("Origin: " + item.getOrigin());
                writer.newLine();
                writer.write("Date: " + item.getPublishedDate().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy")));
                writer.newLine();
                writer.write("Type: " + item.getNewsType());
                writer.newLine();
                writer.write("Title: " + item.getTitle());
                writer.newLine();
                writer.write("URL: " + item.getUrl());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write news items to file: " + e.getMessage());
        }
    }

}
