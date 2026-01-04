package com.pingidentity.pages;

import org.openqa.selenium.By;

public class AppleNewsRoomPageElements {

    // Private constructor to prevent instantiation
    private AppleNewsRoomPageElements() {
    }

    public static final String EXPECTED_TAB_TITLE = "Newsroom - Apple";
    public static final String EXPECTED_URL = "https://www.apple.com/newsroom/";

    public static final By PAGE_TITLE_HEADING = By.xpath("//a[@href='/newsroom/' and contains(text(),'Newsroom')]");
    public static final By PAGE_SUBTITLE_HEADING = By.xpath("//h2[contains(text(),'Latest News')]");
    public static final By NEWS_ARTICLE_BLOCKS = By
            .xpath("//li[contains(@class, 'tile-item')]");
    public static final By ARTICLE_TITLE = By.xpath(".//div[contains(@class, 'tile__headline')]");
    public static final By ARTICLE_URL = By.xpath(".//a[starts-with(@href, '/newsroom/20')]");
    public static final By ARTICLE_DATE = By.xpath(".//div[contains(@class, 'tile__timestamp')]");

}
