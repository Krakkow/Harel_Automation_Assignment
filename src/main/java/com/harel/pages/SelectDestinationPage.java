package com.harel.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.harel.utils.WebDriverCommonFunc;

public class SelectDestinationPage {

    private WebDriverCommonFunc web;

    // Elements

    private By pageTitle;
    private By nextButton;
    private List<By> destinationOptions;

    // Expected values
    private static final String EXPECTED_PAGE_TITLE_TEXT = "בחרו יעד נסיעה";
    private static final int EXPECTED_DESTINATION_OPTIONS_COUNT = 8;

    public SelectDestinationPage(WebDriverCommonFunc web) {
        this.web = web;
        this.pageTitle = By.cssSelector("[data-hrl-bo='screen_title']");
        this.nextButton = By.cssSelector("[data-hrl-bo='wizard-next-button']");
        this.destinationOptions = new ArrayList<>();
        for (int i = 0; i < EXPECTED_DESTINATION_OPTIONS_COUNT; i++) {
            this.destinationOptions.add(By.id("destination-" + i));
        }
    }

    // Validations

    public boolean isPageTitleExist() {
        web.waitUntilVisible(pageTitle);
        return web.doesElementExist(pageTitle) && web.isVisible(pageTitle);
    }

    public boolean isPageTitleCorrect() {
        if (isPageTitleExist()) {
            return web.findElement(pageTitle).getText().equals(EXPECTED_PAGE_TITLE_TEXT);
        }
        throw new IllegalStateException("Page title is not visible");
    }

    public boolean areAllDestinationOptionsPresent() {
        for (By option : destinationOptions) {
            if (!web.doesElementExist(option) || !web.isVisible(option)) {
                throw new IllegalStateException("Destination option not present: " + option.toString());
            }
        }
        return true;
    }

    public boolean isNextButtonVisible() {
        return web.doesElementExist(nextButton) && web.isVisible(nextButton);
    }

    // Actions
    public void selectDestinationByIndex(int index) {
        if (index >= 0 && index < destinationOptions.size()) {
            web.click(destinationOptions.get(index));
        } else {
            throw new IllegalArgumentException("Invalid destination index: " + index);
        }
    }

    public void clickNextButton() {
        if (isNextButtonVisible()) {
            web.click(nextButton);
        } else {
            throw new IllegalStateException("Next button is not visible");
        }
    }

}
