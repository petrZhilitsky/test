package com.epam.auto.test.level2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinuteMailPage {
    private static final String URL = "https://10minutemail.com/";

    private WebDriver webDriver;

    public TenMinuteMailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getEmailAddress() {
        webDriver.get(URL);
        return webDriver.findElement(By.xpath("//input[@id='mailAddress']")).getAttribute("value");
    }

    public String getTotalCost() {
        WebElement openMessage = new WebDriverWait(webDriver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']")));
        openMessage.click();
        return new WebDriverWait(webDriver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mobilepadding']//td[4]")))
                .getText()
                .split(" ")[1];
    }
}
