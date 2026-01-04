package com.pingidentity.pages;

import org.openqa.selenium.By;

public class WhoCovidSummaryPageElements {

    // Private constructor to prevent instantiation
    private WhoCovidSummaryPageElements() {
    }

    // Table 1.1: Test positivity by region
    public static final By TABLE_1_1_ROWS = By.xpath(
            "//h2[contains(text(), 'Table 1.1 SARS-CoV-2 test positivity')]/following::table[1]//tbody/tr");

    public static final By TABLE_1_1_LATEST_DATE = By.xpath(
            "(//h2[contains(text(), 'Table 1.1 SARS-CoV-2 test positivity')]/following::table[1]//thead//th)[last()]");

    // Row-relative selectors:
    public static final By REGION_CELL_IN_ROW = By.xpath("./th");
    public static final By DATA_CELLS_IN_ROW = By.xpath("./td");

    // Table 1.3: Change in test positivity by region
    public static final By TABLE_1_3_ROWS = By.xpath(
            "//h2[contains(text(), 'Table 1.3 Change in SARS-CoV-2 test positivity')]/following::table[1]//tbody/tr");

    public static final By TABLE_1_3_LATEST_DATE = By.xpath(
            "//h2[contains(text(), 'Table 1.3 Change in SARS-CoV-2 test positivity')]/following::table[1]//th[@data-testid='dataDotViz-table-head-last-data-point']");

    // Row-relative selectors for Table 1.3
    public static final By TABLE_1_3_REGION_CELL_IN_ROW = By.xpath("./th");
    public static final By TABLE_1_3_POSITIVITY_SPAN_IN_ROW = By.xpath(
            ".//td[@data-testid='dataDotViz-table-last-data-point']//span");

}
