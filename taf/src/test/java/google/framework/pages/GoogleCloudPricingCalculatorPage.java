package google.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement inputEmail;

    public GoogleCloudPricingCalculatorPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public GoogleCloudPricingCalculatorPage openPage() {
        return this;
    }

    public GoogleCloudPricingCalculatorPage fillForm(String commitmentTerm) {
        webDriver.switchTo().frame(0)
                .switchTo().frame(0);

        WebElement instances = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Number of instances')]/following::input[1]")));
        instances.sendKeys("4");

        //choose machine type
        waitWebElementAndClick("//*[text()='Machine type']/following::md-select[1]");
        waitWebElementAndClick("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']");

        //checkbox 'add GPUs'
        waitWebElementAndClick("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']");

        //choose GPUs number
        waitWebElementAndClick("//*[text()='Number of GPUs']/following::md-select[1]");
        waitWebElementAndClick("//md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]' and @value='1']");

        //choose GPU type
        waitWebElementAndClick("//*[text()='GPU type']/following::md-select[1]");
        waitWebElementAndClick("//md-option[@value='NVIDIA_TESLA_V100']");

        //choose local SSD
        waitWebElementAndClick("//*[text()='Local SSD']/following::md-select[1]");
        waitWebElementAndClick("//md-option[@ng-repeat='item in listingCtrl.supportedSsd' and @value='2']");

        //choose datacenter location
        waitWebElementAndClick("//*[@ng-model='listingCtrl.computeServer.location']");
        waitWebElementAndClick("(//md-option[@value='europe-west3'])[last()]");

        //choose committed usage
        waitWebElementAndClick("//*[@ng-model='listingCtrl.computeServer.cud']");
        waitWebElementAndClick("(//div[text()='" + commitmentTerm + "'])[last()]");

        //click 'add to estimate'
        waitWebElementAndClick("//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']");
        return this;
    }

    public GoogleCloudPricingCalculatorPage sendEmail(String email) {
        webDriver.switchTo().frame(0)
                .switchTo().frame(0);
        waitWebElementAndClick("//button[@id='email_quote']");
        inputEmail.sendKeys(email);
        waitWebElementAndClick("//button[@ng-click='emailQuote.emailQuote(true); emailQuote.$mdDialog.hide()']");
        return this;
    }

    private WebElement waitWebElementAndClick(String xpath) {
        WebElement webElement = new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", webElement);
        return webElement;
    }

    public String getTotalPrice() {
        return new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='resultBlock']//b[@class='ng-binding'])[last()]")))
                .getText();
    }
}
