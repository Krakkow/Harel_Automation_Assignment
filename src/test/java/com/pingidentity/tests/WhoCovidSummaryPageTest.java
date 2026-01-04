package com.pingidentity.tests;

import com.pingidentity.pageutils.WhoCovidSummaryPageUtils;
import com.pingidentity.utils.WebDriverCommonFunc;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class WhoCovidSummaryPageTest extends BaseTest {

    @Test
    public void testTable1_1And1_3PositivityRatesMatch() {
        WebDriverCommonFunc utils = new WebDriverCommonFunc(driver);
        WhoCovidSummaryPageUtils pageUtils = new WhoCovidSummaryPageUtils(driver);

        utils.goToPage("https://data.who.int/dashboards/covid19/summary");

        // Date verification
        String date1 = pageUtils.getLatestDateFromTable1Point1();
        String date3 = pageUtils.getLatestDateFromTable1Point3();
        System.out.println("\n==============================");
        System.out.println("Verifying latest reporting date");
        System.out.println("==============================");
        System.out.println("Latest Date from Table 1.1: " + date1);
        System.out.println("Latest Date from Table 1.3: " + date3);
        Assert.assertEquals(date1, date3, "Mismatch in latest reporting date");
        System.out.println("✅ Latest date verified: " + date1);

        // Extract + print
        Map<String, String> table1_1 = pageUtils.getRegionToPositivityMapFromTable1Point1();
        Map<String, String> table1_3 = pageUtils.getRegionToPositivityMapFromTable1Point3();
        pageUtils.printRegionMap("Extracted Table 1.1 Positivity Rates", table1_1);
        pageUtils.printRegionMap("Extracted Table 1.3 Positivity Rates", table1_3);

        // Compare
        System.out.println("\n==============================");
        System.out.println("Comparing region positivity rates");
        System.out.println("==============================");

        for (String region : table1_1.keySet()) {
            String rate1 = table1_1.get(region);
            String rate3 = table1_3.get(region);

            Assert.assertEquals(rate3, rate1, "Mismatch in positivity rate for region: " + region);
            System.out.println("✅ " + region + " → " + rate1);
        }

        System.out.println("\n==============================");
        System.out.println("🎉 All positivity rates match for date: " + date1);
        System.out.println("==============================");
    }

}
