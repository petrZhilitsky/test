package google.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinuteMailPage extends AbstractPage {

    public TenMinuteMailPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public TenMinuteMailPage openPage() {
        return this;
    }

    public String getEmailAddress() {
        return waitWebElement("//*[@id='i-email']")
                .getAttribute("value");
    }

    public String getTotalCost() {
        waitWebElement("//*[contains(text(),'Google Cloud Sales')]").click();
        webDriver.switchTo().frame(0);
        return waitWebElement("(//tr[1]/td[4])[1]")
                .getText()
                .split(" ")[1];
    }

    private WebElement waitWebElement(String xpath) {
        return new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
