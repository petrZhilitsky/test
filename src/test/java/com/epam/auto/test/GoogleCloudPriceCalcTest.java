package com.epam.auto.test;

import com.epam.auto.test.level2.pages.GoogleCloudMainPage;
import com.epam.auto.test.level2.pages.GoogleCloudPricingCalculatorPage;
import com.epam.auto.test.level2.pages.TenMinuteMailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCloudPriceCalcTest {

    private WebDriver webDriverCalc;
    private WebDriver webDriverMail;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        webDriverCalc = new ChromeDriver();
    }

    @Test
    public void fillFormAndCheckEstimateValues() {
        //'Hurt Me Plenty' task
        new GoogleCloudMainPage(webDriverCalc).openFirstCalcPage();
        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(webDriverCalc).fillForm();
        Assert.assertTrue(pricingCalculatorPage.checkEstimate(), "Estimate values mismatch!");
    }

    @Test
    public void sendEmailAndCheckTotalCost() {
        //'Hardcore' task
        new GoogleCloudMainPage(webDriverCalc).openFirstCalcPage();
        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(webDriverCalc).fillForm();

        webDriverMail = new ChromeDriver();
        TenMinuteMailPage tenMinuteMailPage = new TenMinuteMailPage(webDriverMail);
        String email = tenMinuteMailPage.getEmailAddress();

        pricingCalculatorPage.sendEmail(email);
        String getPriceFromEmail = tenMinuteMailPage.getTotalCost();
        Assert.assertTrue(pricingCalculatorPage.checkTotalPrice(getPriceFromEmail), "Price value mismatch!");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        webDriverCalc.quit();
        webDriverMail.quit();
        webDriverCalc = null;
        webDriverMail = null;
    }
}
