package com.epam.auto.test;

import com.epam.auto.test.level2.pages.PastebinMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PastebinMainPageTest {

    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String TITLE = "how to gain dominance among developers";
    private WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        webDriver = new ChromeDriver();
    }

    @Test(description = "Create New Paste in Pastebin")
    public void createNewPaste() {
        PastebinMainPage newPaste = new PastebinMainPage(webDriver);
        newPaste.createNewPaste();

        Boolean checkNewPasteTitle = newPaste.checkNewPasteTitle(TITLE);
        WebElement checkNewPasteCode = newPaste.checkNewPasteCode(CODE);

        Assert.assertTrue(checkNewPasteTitle, "New Paste title wrong");
        Assert.assertTrue(checkNewPasteCode != null, "New Paste code wrong");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        webDriver.quit();
        webDriver = null;
    }
}
