package com.pingidentity.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pingidentity.POJOs.NewsItem;
import com.pingidentity.pageutils.AppleNewsRoomPageUtils;
import com.pingidentity.utils.LogicFuncs;
import com.pingidentity.utils.WebDriverCommonFunc;

public class AppleNewsRoomPageTest extends BaseTest {

    @Test
    public void validateLatestAppleNewsItems() {
        WebDriverCommonFunc utils = new WebDriverCommonFunc(driver);
        utils.goToPage("https://www.apple.com/newsroom/");

        AppleNewsRoomPageUtils appleUtils = new AppleNewsRoomPageUtils(utils);
        List<NewsItem> articles = appleUtils.getLatestArticles(5);

        Assert.assertEquals(articles.size(), 5, "Expected 5 latest articles");

        for (NewsItem article : articles) {
            Assert.assertNotNull(article.getTitle(), "Article title is null");
            Assert.assertFalse(article.getTitle().trim().isEmpty(), "Article title is empty");

            Assert.assertNotNull(article.getPublishedDate(), "Article date is null");
            Assert.assertTrue(
                    LogicFuncs.isDateFormattedCorrectly(article.getPublishedDate(), "MMMM d, yyyy"),
                    "Date format is invalid: " + article.getPublishedDate());

            Assert.assertNotNull(article.getUrl(), "Article URL is null");
            Assert.assertTrue(article.getUrl().startsWith("https://www.apple.com"), "URL is not absolute");
        }
    }
}
