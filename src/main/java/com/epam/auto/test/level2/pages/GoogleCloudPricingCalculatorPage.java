package com.epam.auto.test.level2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement inputEmail;

    public GoogleCloudPricingCalculatorPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public GoogleCloudPricingCalculatorPage fillForm() {
        webDriver.switchTo().frame(0);
        WebElement instances = webDriver.findElement(By.xpath("//input[@id='input_54']"));
        instances.sendKeys("4");

        WebElement operatingSystems = waitWebElementAndClick("//*[text()='Operating System / Software']/following::md-select[1]");
        WebElement choseOperatingSystem = waitWebElementAndClick("//md-option[@value='free']");

        WebElement machineClasses = waitWebElementAndClick("//*[text()='Machine Class']/following::md-select[1]");
        WebElement choseMachineClass = waitWebElementAndClick("(//md-option[@value='regular'])[last()]");

        WebElement machineTypes = waitWebElementAndClick("//*[text()='Machine type']/following::md-select[1]");
        WebElement choseMachineType = waitWebElementAndClick("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']");

        WebElement addGPUs = waitWebElementAndClick("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']");

        WebElement numbersOfGPUs = waitWebElementAndClick("//*[text()='Number of GPUs']/following::md-select[1]");
        WebElement choseNumberOfGPUs =
                waitWebElementAndClick("//md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]' and @value='1']");

        WebElement typesGPU = waitWebElementAndClick("//*[text()='GPU type']/following::md-select[1]");
        WebElement choseTypeGPU = waitWebElementAndClick("//md-option[@value='NVIDIA_TESLA_V100']");

        WebElement localSSDs = waitWebElementAndClick("//*[text()='Local SSD']/following::md-select[1]");
        WebElement choseLocalSSD = waitWebElementAndClick("//md-option[@ng-repeat='item in listingCtrl.supportedSsd' and @value='2']");

        WebElement datacenterLocations = waitWebElementAndClick("//*[@ng-model='listingCtrl.computeServer.location']");
        WebElement choseDatacenterLocation = waitWebElementAndClick("(//md-option[@value='europe-west3'])[last()]");

        WebElement committedUsages = waitWebElementAndClick("//*[@ng-model='listingCtrl.computeServer.cud']");
        WebElement selectCommittedUsage = waitWebElementAndClick("(//div[text()='1 Year'])[last()]");

        WebElement addToEstimate = waitWebElementAndClick("//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']");
        return this;
    }

    public GoogleCloudPricingCalculatorPage sendEmail(String email) {
        WebElement emailEstimate = waitWebElementAndClick("//button[@id='email_quote']");
        inputEmail.sendKeys(email);
        WebElement sendEmail = waitWebElementAndClick("//button[@ng-click='emailQuote.emailQuote(true); emailQuote.$mdDialog.hide()']");
        return this;
    }

    public boolean checkEstimate() {
        boolean vmClass = checkValue("//*[@id='compute']//md-list-item[2]", "regular");
        boolean instanceType = checkValue("//*[@id='compute']//md-list-item[3]", "n1-standard-8");
        boolean region = checkValue("//*[@id='compute']//md-list-item[4]", "Frankfurt");
        boolean localSSD = checkValue("//*[@id='compute']//md-list-item[5]", "2x375 GB");
        boolean commitmentTerm = checkValue("//*[@id='compute']//md-list-item[6]", "1 Year");
        return (vmClass && instanceType && region && localSSD && commitmentTerm);
    }

    public boolean checkTotalPrice(String price) {
        return new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='resultBlock']//b[@class='ng-binding'])[last()]")))
                .getText()
                .contains(price);
    }

    private WebElement waitWebElementAndClick(String xpath) {
        WebElement webElement = new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        webElement.click();
        return webElement;
    }

    private boolean checkValue(String xpath, String value) {
        return new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
                .getText()
                .contains(value);
    }
}
