package com.pingidentity.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pingidentity.POJOs.NewsItem;
import com.pingidentity.pageutils.AppleNewsRoomPageUtils;
import com.pingidentity.pageutils.WhoNewsPageUtils;
import com.pingidentity.utils.LogicFuncs;
import com.pingidentity.utils.WebDriverCommonFunc;

public class WhoNewsPageTest extends BaseTest {

    @Test
    public void validateWhoNewsStatements() {

        WebDriverCommonFunc utils = new WebDriverCommonFunc(driver);
        WhoNewsPageUtils whoUtils = new WhoNewsPageUtils(utils);

        utils.goToPage("https://www.who.int/news");

        List<NewsItem> newsList = whoUtils.getArticlesByType(5, "Statement");

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < newsList.size(); i++) {
            NewsItem item = newsList.get(i);
            String prefix = "Item " + (i + 1) + ": ";

            softAssert.assertFalse(item.getTitle().isEmpty(), prefix + "Title should not be empty");
            softAssert.assertTrue(
                    LogicFuncs.isDateFormattedCorrectly(item.getPublishedDate(), "d MMMM yyyy"),
                    prefix + "Date should match format d MMMM yyyy");
            softAssert.assertTrue(
                    item.getUrl().startsWith("https:"),
                    prefix + "URL should start with https:");
            softAssert.assertTrue(
                    item.getUrl().contains(String.valueOf(item.getPublishedDate().getYear())),
                    prefix + "URL should contain year " + item.getPublishedDate().getYear());
        }

        softAssert.assertAll();
    }

    @Test
    public void validateWhoNewsJointReleases() {

        WebDriverCommonFunc utils = new WebDriverCommonFunc(driver);
        WhoNewsPageUtils whoUtils = new WhoNewsPageUtils(utils);

        utils.goToPage("https://www.who.int/news");

        List<NewsItem> newsList = whoUtils.getArticlesByType(5, "Joint News Release");

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < newsList.size(); i++) {
            NewsItem item = newsList.get(i);
            String prefix = "Item " + (i + 1) + ": ";

            softAssert.assertFalse(item.getTitle().isEmpty(), prefix + "Title should not be empty");
            softAssert.assertTrue(
                    LogicFuncs.isDateFormattedCorrectly(item.getPublishedDate(), "d MMMM yyyy"),
                    prefix + "Date should match format d MMMM yyyy");
            softAssert.assertTrue(
                    item.getUrl().startsWith("https:"),
                    prefix + "URL should start with https:");
            softAssert.assertTrue(
                    item.getUrl().contains(String.valueOf(item.getPublishedDate().getYear())),
                    prefix + "URL should contain year " + item.getPublishedDate().getYear());
        }

        softAssert.assertAll();
    }

    @Test
    public void exportCombinedNewsToFile() {
        WebDriverCommonFunc utils = new WebDriverCommonFunc(driver);

        // WHO News
        utils.goToPage("https://www.who.int/news-room/releases");
        WhoNewsPageUtils whoUtils = new WhoNewsPageUtils(utils);
        List<NewsItem> whoNews = whoUtils.getArticlesByType(5, "Joint News Release");

        // Apple News
        utils.goToPage("https://www.apple.com/newsroom/");
        AppleNewsRoomPageUtils appleUtils = new AppleNewsRoomPageUtils(utils);
        List<NewsItem> appleNews = appleUtils.getLatestArticles(5);

        // Combine
        List<NewsItem> combined = new ArrayList<>();
        combined.addAll(whoNews);
        combined.addAll(appleNews);

        // Export to file
        LogicFuncs.exportNewsToFile(combined, "latest_news.txt");
    }

}
