package com.harel.pages;

import org.openqa.selenium.By;

import com.harel.utils.WebDriverCommonFunc;

public class LandingPage {

    private WebDriverCommonFunc web;

    // Elements
    private By logo;
    private By title;
    private By subtitle;
    private By firstPurchaseButton;

    // expected texts
    private static final String EXPECTED_TITLE_TEXT = "הגעת להראל";
    private static final String EXPECTED_SUBTITLE_TEXT = "המקום הכי בריא לרכישת ביטוח נסיעות לחו\"ל.";

    public LandingPage(WebDriverCommonFunc web) {
        this.web = web;
        this.logo = By.id("logo-harel-logo");
        this.title = By.cssSelector("[data-hrl-bo='landing_page_title']");
        this.subtitle = By.cssSelector("[data-hrl-bo='landing_page_subtitle']");
        this.firstPurchaseButton = By.cssSelector("[data-hrl-bo='purchase-for-new-customer']");
    }

    // Validations
    public boolean isLogoVisible() {
        return web.doesElementExist(logo) && web.isVisible(logo);
    }

    public boolean isTitleCorrect() {
        return web.doesElementExist(title) && web.isVisible(title)
                && web.findElement(title).getText().equals(EXPECTED_TITLE_TEXT);
    }

    public boolean isSubtitleCorrect() {
        return web.doesElementExist(subtitle) && web.isVisible(subtitle)
                && web.findElement(subtitle).getText().equals(EXPECTED_SUBTITLE_TEXT);
    }

    public boolean isFirstTimePurchaseButtonExist() {
        return web.doesElementExist(firstPurchaseButton) && web.isVisible(firstPurchaseButton);
    }

    // Actions
    public void clickFirstPurchaseButton() {
        web.click(firstPurchaseButton);
    }

}
