package com.pingidentity.pageutils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.pingidentity.POJOs.NewsItem;
import com.pingidentity.pages.AppleNewsRoomPageElements;
import com.pingidentity.utils.WebDriverCommonFunc;

public class AppleNewsRoomPageUtils {

    private final WebDriverCommonFunc utils;

    public AppleNewsRoomPageUtils(WebDriverCommonFunc utils) {
        this.utils = utils;
    }

    public List<NewsItem> getLatestArticles(int maxCount) {
        List<WebElement> articleBlocks = utils.findElements(AppleNewsRoomPageElements.NEWS_ARTICLE_BLOCKS);
        List<NewsItem> newsList = new ArrayList<>();

        int count = 0;
        for (WebElement article : articleBlocks) {
            if (count >= maxCount)
                break;

            NewsItem item = extractNewsItem(article);
            if (item != null) {
                newsList.add(item);
                count++;
            }
        }

        return newsList;
    }

    private NewsItem extractNewsItem(WebElement article) {
        return utils.extractNewsItemFromBlock(
                article,
                AppleNewsRoomPageElements.ARTICLE_TITLE,
                AppleNewsRoomPageElements.ARTICLE_DATE,
                "MMMM d, yyyy",
                "https://www.apple.com/newsroom/",
                "N/A");
    }
}
