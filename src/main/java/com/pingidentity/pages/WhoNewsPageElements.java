package com.pingidentity.pages;

import org.openqa.selenium.By;

public class WhoNewsPageElements {

        // Private constructor to prevent instantiation
        private WhoNewsPageElements() {
        }

        public static final String EXPECTED_TAB_TITLE = "News";
        public static final String EXPECTED_URL = "https://www.who.int/news";

        public static final By PAGE_TITLE_HEADING = By.xpath("//h1[contains(text(), 'News')]");
        public static final By PAGE_SUBTITLE_HEADING = By
                        .xpath("//p[contains(text(), 'Browse selected WHO news below' )]");
        public static final By NEWS_TYPE_DROPDOWN = By
                        .xpath("//input[@placeholder='News type']/ancestor::span[contains(@class,'k-dropdown-wrap')]//span[contains(@class,'k-i-arrow-60-down')]");
        public static final By NEWS_TYPE_DROPDOWN_OPTIONS = By.xpath("//ul[contains(@id,'newstypes_listbox')]//li");
        public static final By NEWS_ARTICLE_BLOCKS = By
                        .xpath("//div[contains(@class, 'list-view--item vertical-list-item')]");
        public static final By ARTICLE_TITLE = By.xpath(".//a[contains(@class, 'link-container')]");
        public static final By ARTICLE_URL = By.xpath(".//a[contains(@class, 'link-container')]");
        public static final By SELECTED_NEWS_TYPE = By
                        .xpath("//input[@class='k-input combobox' and @placeholder='News type']");
        public static final By ARTICLE_DATE = By.xpath(".//span[contains(@class, 'timestamp')]");

}
