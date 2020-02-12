package google.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        //choose language to prevent double calculator loading
        waitWebElementAndClick("(//*[@class='devsite-select-toggle'])[1]");
        waitWebElementAndClick("(//li[text()='English'])[1]");

        //input search text
        WebElement inputSearch = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='q']")));
        inputSearch.sendKeys(SEARCH_CALC);
        inputSearch.submit();
        return this;
    }

    public GoogleCloudPricingCalculatorPage openFirstCalcPage() {
        waitWebElementAndClick("(//b[text()='" + SEARCH_CALC + "'])[1]");
        return new GoogleCloudPricingCalculatorPage(webDriver);
    }

    private WebElement waitWebElementAndClick(String xpath) {
        WebElement webElement = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        webElement.click();
        return webElement;
    }
}
