package com.harel.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.harel.utils.WebDriverCommonFunc;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverCommonFunc webDriverCommonFunc;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup(); // this downloads and sets path automatically
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverCommonFunc = new WebDriverCommonFunc(driver);

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
