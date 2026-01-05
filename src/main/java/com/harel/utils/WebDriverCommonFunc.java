package com.harel.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverCommonFunc {

    private WebDriver driver;
    private WebDriverWait wait;
    private String titleAttribute = "aria-label";
    private String hrefAttribute = "href";

    public WebDriverCommonFunc(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void goToPage(String url) {
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        } else {
            throw new IllegalArgumentException("URL must not be null or empty");
        }
    }

    public WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean doesElementExist(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void click(By locator) {
        waitUntilVisible(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitUntilVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void typeAndSubmit(By locator, String text) {
        WebElement element = waitUntilVisible(locator);
        element.clear();
        element.sendKeys(text);
        element.submit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getTextFromElement(By locator) {
        return waitUntilVisible(locator).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void filterByDropdownOption(
            By dropdownLocator,
            By optionLocators,
            By selectedLocator,
            String optionToSelect,
            By articleBlocks) {
        click(dropdownLocator);

        WebElement existingFirstArticle = findElement(articleBlocks); // save current state
        List<WebElement> options = waitForAllVisible(optionLocators);
        boolean found = false;

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(optionToSelect)) {
                option.click();
                found = true;
                break;
            }
        }

        // Wait for the article list to refresh
        wait.until(ExpectedConditions.stalenessOf(existingFirstArticle));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(articleBlocks));

        if (!found) {
            throw new IllegalArgumentException("Option '" + optionToSelect + "' not found in dropdown.");
        }

        // Optional: verify selection
        String selected = findElement(selectedLocator).getAttribute("value");
        if (!selected.equalsIgnoreCase(optionToSelect)) {
            throw new IllegalStateException("Expected '" + optionToSelect + "', but found '" + selected + "'");
        }
    }

}
