package com.harel.pages;

import org.openqa.selenium.By;

import com.harel.utils.WebDriverCommonFunc;

public class PassengersDetailsPage {

    private WebDriverCommonFunc web;

    // Elements
    private By title;

    // Expected values
    private static final String EXPECTED_TITLE_TEXT = "נשמח להכיר את הנוסעים שנבטח הפעם";

    public PassengersDetailsPage(WebDriverCommonFunc web) {
        this.web = web;
        this.title = By.cssSelector("[data-hrl-bo='screen_title']");
    }

    // Validations
    public boolean isTitleExist() {
        return web.doesElementExist(title) && web.isVisible(title);
    }

    public boolean isTitleTextCorrect() {
        if (isTitleExist()) {
            return web.findElement(title).getText().equals(EXPECTED_TITLE_TEXT);
        }
        throw new IllegalStateException("Title is not visible");
    }

}
