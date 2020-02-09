package google.framework.test;

import google.framework.model.PriceCalcData;
import google.framework.pages.GoogleCloudMainPage;
import google.framework.pages.GoogleCloudPricingCalculatorPage;
import google.framework.pages.TenMinuteMailPage;
import google.framework.service.PriceCalcDataCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class GoogleCloudPriceCalcTest extends CommonConditions {
    @Test
    public void findGoogleCloudCalcFillFormSendEmailAndCheckTotalCost() {
        PriceCalcData testData = PriceCalcDataCreator.withDataFromProperty();
        GoogleCloudPricingCalculatorPage calcPage = new GoogleCloudMainPage(webDriver)
                .openPage()
                .inputSearchAndSubmit()
                .openFirstCalcPage()
                .fillForm(testData.getCommitmentTerm());
        String totalCostFromCalc = calcPage.getTotalPrice();

        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("window.open('https://10minutemail.com','_blank');");
        TenMinuteMailPage emailPage = new TenMinuteMailPage(webDriver);

        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(tabs.get(1));
        String email = emailPage.getEmailAddress();

        webDriver.switchTo().window(tabs.get(0));
        calcPage.sendEmail(email);

        webDriver.switchTo().window(tabs.get(1));
        String totalCostFromEmail = emailPage.getTotalCost();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(totalCostFromCalc.contains(testData.getTotalCost()));
        softAssert.assertTrue(totalCostFromEmail.contains(testData.getTotalCost()));
        softAssert.assertAll();
    }
}
