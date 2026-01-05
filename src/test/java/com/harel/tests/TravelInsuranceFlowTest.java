package com.harel.tests;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.harel.base.BaseTest;
import com.harel.pages.DatePickingPage;
import com.harel.pages.LandingPage;
import com.harel.pages.PassengersDetailsPage;
import com.harel.pages.SelectDestinationPage;
import com.harel.utils.WebDriverCommonFunc;

public class TravelInsuranceFlowTest extends BaseTest {

    private static final String BASE_URL = "https://digital.harel-group.co.il/travel-policy";
    private static final int DEPARTURE_PLUS_DAYS = 7;
    private static final int RETURN_PLUS_DAYS = 30;

    @Test
    public void travelInsurancePurchaseFlow() {

        WebDriverCommonFunc web = new WebDriverCommonFunc(driver);

        // -------- Landing Page --------
        web.goToPage(BASE_URL);

        LandingPage landingPage = new LandingPage(web);
        Assert.assertTrue(landingPage.isTitleCorrect(), "Landing page title text should be correct");
        Assert.assertTrue(landingPage.isSubtitleCorrect(), "Landing page subtitle text should be correct");
        Assert.assertTrue(landingPage.isLogoVisible(), "Landing page logo should exist");
        Assert.assertTrue(landingPage.isFirstTimePurchaseButtonExist(), "First-time purchase button should exist");

        landingPage.clickFirstPurchaseButton();

        // -------- Select Destination Page --------
        SelectDestinationPage selectDestinationPage = new SelectDestinationPage(web);
        Assert.assertTrue(selectDestinationPage.isPageTitleExist(), "Destination page title should exist");
        Assert.assertTrue(selectDestinationPage.isPageTitleCorrect(), "Destination page title text should be correct");
        Assert.assertTrue(selectDestinationPage.areAllDestinationOptionsPresent(), "All 8 destinations should exist");
        Assert.assertTrue(selectDestinationPage.isNextButtonVisible(), "Next button should exist");

        selectDestinationPage.selectDestinationByIndex(0); // destination-0
        selectDestinationPage.clickNextButton();

        // -------- Trip Dates Page --------
        DatePickingPage pickingDatesPage = new DatePickingPage(web);

        Assert.assertTrue(pickingDatesPage.isDepartureTitleDisplayed(), "Departure title should exist");
        Assert.assertTrue(pickingDatesPage.isReturnTitleDisplayed(), "Return title should exist");
        Assert.assertTrue(pickingDatesPage.isDepartureTitleTextCorrect(), "Departure title text should be correct");
        Assert.assertTrue(pickingDatesPage.isReturnTitleTextCorrect(), "Return title text should be correct");

        Assert.assertTrue(pickingDatesPage.isDepartureInputDisplayed(), "Departure input should exist");
        Assert.assertTrue(pickingDatesPage.isReturnInputDisplayed(), "Return input should exist");
        Assert.assertTrue(pickingDatesPage.isNextButtonDisplayed(), "Next button should exist");

        // Calculate dates (can be moved to LogicFuncs later)
        LocalDate departureDate = LocalDate.now().plusDays(DEPARTURE_PLUS_DAYS);

        // Departure: input field (dd/MM/yyyy) — per assignment
        pickingDatesPage.setDepartureDate(); // uses LogicFunc(s) placeholder inside the page

        // Return: widget only — departure + 30
        pickingDatesPage.setReturnDateUsingWidget(departureDate, RETURN_PLUS_DAYS);

        // Validate total days indicator appears and equals 30
        Assert.assertTrue(pickingDatesPage.isTotalDaysIndicatorExist(),
                "Total days indicator should appear after setting return date");
        Assert.assertTrue(pickingDatesPage.isTotalDaysCountCorrect(), "Total days should be 31");

        pickingDatesPage.clickNext();

        // -------- Passengers Details Page --------
        PassengersDetailsPage passengersDetailsPage = new PassengersDetailsPage(web);

        Assert.assertTrue(passengersDetailsPage.isTitleExist(), "Passengers Details page title should exist");
        Assert.assertTrue(passengersDetailsPage.isTitleTextCorrect(),
                "Passengers Details page title text should be correct");
    }
}
