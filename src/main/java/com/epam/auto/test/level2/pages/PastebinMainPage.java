package com.epam.auto.test.level2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinMainPage {
    private static final String URL = "https://pastebin.com/";
    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String TITLE = "how to gain dominance among developers";
    private WebDriver webDriver;

    @FindBy(xpath = "//textarea[@id='paste_code']")
    private WebElement newPaste;

    @FindBy(xpath = "//input[@name='paste_name']")
    private WebElement pasteName;

    @FindBy(xpath = "//option[@value='10M' and text()='10 Minutes']")
    private WebElement pasteExpiration;

    @FindBy(xpath = "//option[@value='8' and text()='Bash']")
    private WebElement syntaxHighlighting;

    public PastebinMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public PastebinMainPage createNewPaste() {
        webDriver.get(URL);
        newPaste.sendKeys(CODE);
        pasteName.sendKeys(TITLE);
        pasteExpiration.click();
        syntaxHighlighting.click();
        newPaste.submit();
        return this;
    }

    public boolean checkNewPasteTitle(String title) {
        return this.webDriver.getTitle().contains(title);
    }

    public WebElement checkNewPasteCode(String code) {
        return this.webDriver.findElement(By.xpath("//*[text()='" + code + "']"));
    }
}
