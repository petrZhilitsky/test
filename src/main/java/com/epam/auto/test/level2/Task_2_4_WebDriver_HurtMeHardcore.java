package com.epam.auto.test.level2;

import com.epam.auto.test.level2.pages.GoogleCloudMainPage;
import com.epam.auto.test.level2.pages.GoogleCloudPricingCalculatorPage;
import com.epam.auto.test.level2.pages.TenMinuteMailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Task_2_4_WebDriver_HurtMeHardcore {

    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriverCalc = new ChromeDriver();

        new GoogleCloudMainPage(webDriverCalc).openFirstCalcPage();
        GoogleCloudPricingCalculatorPage pricingCalculatorPage = new GoogleCloudPricingCalculatorPage(webDriverCalc).fillForm();

        WebDriver webDriverMail = new ChromeDriver();
        TenMinuteMailPage tenMinuteMailPage = new TenMinuteMailPage(webDriverMail);
        String email = tenMinuteMailPage.getEmailAddress();
        pricingCalculatorPage.sendEmail(email);

        TimeUnit.SECONDS.sleep(5);
        webDriverCalc.quit();
        webDriverMail.quit();
    }
}
