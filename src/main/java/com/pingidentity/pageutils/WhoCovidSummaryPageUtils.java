package com.pingidentity.pageutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pingidentity.pages.WhoCovidSummaryPageElements;
import com.pingidentity.utils.WebDriverCommonFunc;

public class WhoCovidSummaryPageUtils {

    private WebDriverCommonFunc common;

    public WhoCovidSummaryPageUtils(WebDriver driver) {
        this.common = new WebDriverCommonFunc(driver);
    }

    public String getLatestDateFromTable1Point1() {
        common.waitUntilVisible(WhoCovidSummaryPageElements.TABLE_1_1_LATEST_DATE);
        return common.getTextFromElement(WhoCovidSummaryPageElements.TABLE_1_1_LATEST_DATE);
    }

    public String getLatestDateFromTable1Point3() {
        common.waitUntilVisible(WhoCovidSummaryPageElements.TABLE_1_3_LATEST_DATE);
        return common.getTextFromElement(WhoCovidSummaryPageElements.TABLE_1_3_LATEST_DATE);
    }

    public Map<String, String> getRegionToPositivityMapFromTable1Point1() {
        Map<String, String> result = new HashMap<>();
        List<WebElement> rows = common.findElements(WhoCovidSummaryPageElements.TABLE_1_1_ROWS);

        System.out.println("\n==============================");
        System.out.println("📊 Extracting Table 1.1 Positivity Rates");
        System.out.println("==============================");

        for (WebElement row : rows) {
            String region = "";
            String positivity = "";

            try {
                WebElement regionCell = row.findElement(WhoCovidSummaryPageElements.REGION_CELL_IN_ROW);
                region = regionCell.getText().trim();
            } catch (Exception e) {
                System.out.println("⚠ Region <th> not found → Row skipped.");
                continue;
            }

            List<WebElement> cells = row.findElements(WhoCovidSummaryPageElements.DATA_CELLS_IN_ROW);
            if (!cells.isEmpty()) {
                positivity = cells.get(cells.size() - 1).getText().trim();
            } else {
                System.out.println("⚠ No <td> cells found for region: " + region);
            }

            result.put(region, positivity);
            // System.out.println("✅ " + region + " → " + positivity);
        }

        return result;
    }

    public Map<String, String> getRegionToPositivityMapFromTable1Point3() {
        Map<String, String> result = new HashMap<>();
        List<WebElement> rows = common.findElements(WhoCovidSummaryPageElements.TABLE_1_3_ROWS);

        System.out.println("\n==============================");
        System.out.println("📊 Extracting Table 1.3 Positivity Rates");
        System.out.println("==============================");

        for (WebElement row : rows) {
            String region = "";
            String positivity = "";

            try {
                WebElement regionCell = row.findElement(WhoCovidSummaryPageElements.TABLE_1_3_REGION_CELL_IN_ROW);
                region = regionCell.getText().trim();
            } catch (Exception e) {
                System.out.println("⚠ Region <th> not found → Row skipped.");
                continue;
            }

            try {
                WebElement positivitySpan = row
                        .findElement(WhoCovidSummaryPageElements.TABLE_1_3_POSITIVITY_SPAN_IN_ROW);
                positivity = positivitySpan.getText().trim();
            } catch (Exception e) {
                System.out.println("⚠ No value for region: " + region);
            }

            result.put(region, positivity);
            // System.out.println("✅ " + region + " → " + (positivity.isEmpty() ? "[empty]"
            // : positivity));
        }

        return result;
    }

    public void printRegionMap(String title, Map<String, String> data) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
        if (data == null || data.isEmpty()) {
            System.out.println("⚠ No data to display.");
            return;
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println("Region: " + entry.getKey() + " → Positivity Rate: " + entry.getValue());
        }
    }

}
