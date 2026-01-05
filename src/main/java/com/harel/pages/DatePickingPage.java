package com.harel.pages;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harel.utils.WebDriverCommonFunc;

import com.harel.utils.LogicFunc;

public class DatePickingPage {

    private WebDriverCommonFunc web;

    // Elemetns
    private By departueTitle;
    private By returnTitle;
    private By departueDateInput;
    private By returnDateInput;
    private By nextMonthButon;
    private By previousMonthButton;
    private By backButton;
    private By nextButton;

    // Expected values
    private static final String EXPECTED_DEPARTUE_TITLE_TEXT = "מתי יוצאים?";
    private static final String EXPECTED_RETURN_TITLE_TEXT = "מתי חוזרים?";
    private static final String EXPECTED_TOTAL_DAYS = "סה\"כ: 31 ימים";

    public DatePickingPage(WebDriverCommonFunc web) {
        this.web = web;
        this.departueTitle = By.cssSelector("[data-hrl-bo='travel_start_date']");
        this.returnTitle = By.xpath("//div[@data-hrl-bo='bo-textField-endDateInput']/preceding-sibling::h2");
        this.departueDateInput = By.cssSelector("[data-hrl-bo='startDateInput_input']");
        this.returnDateInput = By.cssSelector("[data-hrl-bo='endDateInput_input']");
        this.nextMonthButon = By.cssSelector("[data-hrl-bo='arrow-forward']");
        this.previousMonthButton = By.cssSelector("[data-hrl-bo='arrow-back']");
        this.backButton = By.cssSelector("[data-hrl-bo='wizard-back-button']");
        this.nextButton = By.cssSelector("[data-hrl-bo='wizard-next-button']");
    }

    // Validations
    public boolean isDepartureTitleDisplayed() {
        return web.doesElementExist(departueTitle) && web.isVisible(departueTitle);
    }

    public boolean isReturnTitleDisplayed() {
        return web.doesElementExist(returnTitle) && web.isVisible(returnTitle);
    }

    public boolean isDepartureTitleTextCorrect() {
        if (isDepartureTitleDisplayed()) {
            return web.getTextFromElement(departueTitle).equals(EXPECTED_DEPARTUE_TITLE_TEXT);
        }
        throw new IllegalStateException("Departure title is not visible");
    }

    public boolean isReturnTitleTextCorrect() {
        if (isReturnTitleDisplayed()) {
            return web.getTextFromElement(returnTitle).equals(EXPECTED_RETURN_TITLE_TEXT);
        }
        throw new IllegalStateException("Return title is not visible");
    }

    public boolean isDepartureInputDisplayed() {
        return web.doesElementExist(departueDateInput) && web.isVisible(departueDateInput);
    }

    public boolean isReturnInputDisplayed() {
        return web.doesElementExist(returnDateInput) && web.isVisible(returnDateInput);
    }

    public boolean isNextButtonDisplayed() {
        return web.doesElementExist(nextButton) && web.isVisible(nextButton);
    }

    public boolean isBackButtonDisplayed() {
        return web.doesElementExist(backButton) && web.isVisible(backButton);
    }

    public boolean isNextMonthButtonDisplayed() {
        return web.doesElementExist(nextMonthButon) && web.isVisible(nextMonthButon);
    }

    public boolean isPreviousMonthButtonDisplayed() {
        return web.doesElementExist(previousMonthButton) && web.isVisible(previousMonthButton);
    }

    // Actions

    /**
     * Sets the departure date to today + 7 days using the input field.
     * Uses LogicFunc for the date calculation (pure Java).
     */
    public void setDepartureDate() {
        String formattedDate = LogicFunc.getFormattedDateFromToday(7);
        web.click(departueDateInput);
        web.type(departueDateInput, formattedDate);
    }

    public void setReturnDateUsingWidget(LocalDate departureDate, int daysToAdd) {

        // 1) Calculate return date
        LocalDate returnDate = LogicFunc.addDays(departureDate, daysToAdd);
        String returnDateIso = LogicFunc.formatDateForWidget(returnDate);

        // 2) Click return input to focus the widget
        web.click(returnDateInput);

        // 3) Ensure we are in the correct month (safe approach):
        // Try click directly. If not found, move months forward until it exists.
        By returnDayLocator = getCalendarDayByIsoDate(returnDateIso);

        int safetyCounter = 0;
        while (!web.doesElementExist(returnDayLocator) && safetyCounter < 24) {
            web.click(nextMonthButon);
            safetyCounter++;
        }

        if (!web.doesElementExist(returnDayLocator)) {
            throw new IllegalStateException(
                    "Return date '" + returnDateIso + "' was not found in the calendar after navigating months.");
        }

        // 4) Click the return date in the widget
        web.click(returnDayLocator);

        isTotalDaysCountCorrect();
    }

    public void clickNext() {
        web.click(nextButton);
    }

    public void clickBack() {
        web.click(backButton);
    }

    public void goToNextMonthInPicker() {
        web.click(nextMonthButon);
    }

    public void goToPreviousMonthInPicker() {
        web.click(previousMonthButton);
    }

    public boolean isTotalDaysIndicatorExist() {
        By totalDaysIndicator = By.cssSelector("[data-hrl-bo='total-days']");
        return web.doesElementExist(totalDaysIndicator) && web.isVisible(totalDaysIndicator);
    }

    public boolean isTotalDaysCountCorrect() {
        String actualDays = getTotalDaysCount();
        if (actualDays.equals(EXPECTED_TOTAL_DAYS)) {
            return true;
        } else {
            throw new IllegalStateException(
                    "Total days count is incorrect. Expected: " + EXPECTED_TOTAL_DAYS + ", Actual: " + actualDays);
        }
    }

    private By getCalendarDayByIsoDate(String isoDate) {
        return By.cssSelector("[data-hrl-bo='" + isoDate + "']");
    }

    private String getTotalDaysCount() {
        WebElement totalDaysElement = web.findElement(By.cssSelector("[data-hrl-bo='total-days']"));
        return (totalDaysElement.getText());
    }
}
