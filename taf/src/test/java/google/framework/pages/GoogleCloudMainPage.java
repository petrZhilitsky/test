package google.framework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GoogleCloudMainPage extends AbstractPage {
    private static final String URL = "https://cloud.google.com/";
    private static final String SEARCH_CALC = "Google Cloud Platform Pricing Calculator";

    public GoogleCloudMainPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public GoogleCloudMainPage openPage() {
        webDriver.navigate().to(URL);
        return this;
    }

    public GoogleCloudMainPage inputSearchAndSubmit() {
        WebElement openLangs = waitWebElementAndClick("(//*[@class='devsite-select-toggle'])[1]");
        WebElement choseLang = waitWebElementAndClick("(//li[text()='English'])[1]");

        WebElement inputSearch = waitWebElement("//input[@name='q']");
        inputSearch.sendKeys(SEARCH_CALC);
        inputSearch.submit();
        return this;
    }

    public GoogleCloudPricingCalculatorPage openFirstCalcPage() {
        WebElement calcSearchResult = waitWebElement("(//b[text()='" + SEARCH_CALC + "'])[1]");
        calcSearchResult.click();
        return new GoogleCloudPricingCalculatorPage(webDriver);
    }

    private WebElement waitWebElement(String xpath) {
        return new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private WebElement waitWebElementAndClick(String xpath) {
        WebElement webElement = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        webElement.click();
        return webElement;
    }
}
