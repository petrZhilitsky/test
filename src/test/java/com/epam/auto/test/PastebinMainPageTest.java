package com.epam.auto.test;

import com.epam.auto.test.level2.pages.PastebinMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PastebinMainPageTest {

    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String TITLE = "how to gain dominance among developers";
    private static final String BASH = "Bash";
    private WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        webDriver = new ChromeDriver();
    }

    @Test(description = "Create New Paste in Pastebin")
    public void createNewPaste_test() {
        PastebinMainPage newPaste = new PastebinMainPage(webDriver);
        newPaste.createNewPaste();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newPaste.checkNewPasteTitle(TITLE), "New Paste title wrong");
        softAssert.assertNotNull(newPaste.checkNewPasteCode(CODE), "New Paste code wrong");
        softAssert.assertNotNull(newPaste.checkBashHighlight(BASH), "New Paste Bash highlighting failed");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        webDriver.quit();
        webDriver = null;
    }
}
