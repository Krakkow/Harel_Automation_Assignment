package com.pingidentity.POJOs;

import java.time.LocalDate;

public class NewsItem {
    private String origin;
    private LocalDate publishedDate;
    private String newsType;
    private String title;
    private String url;

    public NewsItem(String origin, LocalDate publishedDate, String newsType, String title, String url) {
        this.origin = origin;
        this.publishedDate = publishedDate;
        this.newsType = newsType;
        this.title = title;
        this.url = url;
    }

    public String getOrigin() {
        return origin;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public String getNewsType() {
        return newsType;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "WhoNewsItem [origin=" + origin + ", publishedDate=" + publishedDate + ", newType=" + newsType
                + ", title=" + title + ", url=" + url + "]";
    }

}
