package com.epam.auto.test.level2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudMainPage {
    private static final String URL = "https://cloud.google.com/";
    private static final String SEARCH_CALC = "Google Cloud Platform Pricing Calculator";

    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement inputSearch;

    @FindBy(xpath = "//b[text()='" + SEARCH_CALC + "']/parent::a[@class='gs-title']")
    private WebElement calcSearchResult;

    public GoogleCloudMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public GoogleCloudMainPage openFirstCalcPage() {
        webDriver.get(URL);
        inputSearch.sendKeys(SEARCH_CALC);
        inputSearch.submit();
        calcSearchResult.click();
        return this;
    }
}
