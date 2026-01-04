package com.pingidentity.pageutils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.pingidentity.POJOs.NewsItem;
import com.pingidentity.pages.WhoNewsPageElements;
import com.pingidentity.utils.WebDriverCommonFunc;

public class WhoNewsPageUtils {

    private final WebDriverCommonFunc utils;

    public WhoNewsPageUtils(WebDriverCommonFunc utils) {
        this.utils = utils;
    }

    public List<NewsItem> getArticlesByType(int maxCount, String expectedType) {
        utils.filterByDropdownOption(
                WhoNewsPageElements.NEWS_TYPE_DROPDOWN,
                WhoNewsPageElements.NEWS_TYPE_DROPDOWN_OPTIONS,
                WhoNewsPageElements.SELECTED_NEWS_TYPE,
                expectedType,
                WhoNewsPageElements.NEWS_ARTICLE_BLOCKS);

        List<WebElement> articleBlocks = utils.findElements(WhoNewsPageElements.NEWS_ARTICLE_BLOCKS);
        List<NewsItem> newsList = new ArrayList<>();

        int count = 0;
        for (WebElement article : articleBlocks) {
            if (count >= maxCount)
                break;

            NewsItem item = extractNewsItem(article, expectedType);
            if (item != null) {
                newsList.add(item);
                count++;
            }
        }

        return newsList;
    }

    private NewsItem extractNewsItem(WebElement article, String type) {
        return utils.extractNewsItemFromBlock(
                article,
                WhoNewsPageElements.ARTICLE_TITLE,
                WhoNewsPageElements.ARTICLE_DATE,
                "d MMMM yyyy",
                "https://www.who.int/news",
                type,
                true // WHO uses aria-label
        );
    }

}
