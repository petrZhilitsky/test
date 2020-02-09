package google.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        return webDriver.findElement(By.xpath("//input[@id='mailAddress']")).getAttribute("value");
    }

    public String getTotalCost() {
        WebElement openMessage = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']")));
        openMessage.click();
        return new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mobilepadding']//td[4]")))
                .getText()
                .split(" ")[1];
    }
}
