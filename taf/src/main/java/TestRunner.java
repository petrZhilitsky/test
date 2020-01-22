import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YandexDiskAccountPage;
import pages.YandexDiskMainPage;

import java.util.Date;

public class TestRunner {
    private WebDriver webDriver;
    private static final String LOGIN = "petr.zhilitsky";
    private static final String PASSWORD = "Gomel2020";
    private static final String CURRENT_FOLDER_NAME = "Folder" + new Date().getTime();
    private static final String CURRENT_FILE_NAME = "File" + new Date().getTime();
    private static final String INPUT_TEXT = "Hello World from Gomel!";

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void logInYandexDiskAccountPositive() {
        YandexDiskMainPage mainPageLogIn = new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        Assert.assertTrue(mainPageLogIn.checkLoggedIn(LOGIN), "Log In failed");
    }

    @Test(priority = 2)
    public void logInYandexDiskAccountNegative() {
        YandexDiskMainPage mainPageLogIn = new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD + "!");
        Assert.assertFalse(mainPageLogIn.checkLoggedIn(LOGIN), "Log In succeeded");
    }

    @Test(priority = 3)
    public void checkNavigationMenuItems() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        Assert.assertTrue(new YandexDiskAccountPage(webDriver).checkMainMenuItems(), "Navigation URLs incorrect");
    }

    @Test(priority = 4)
    public void createAndVisitNewFolder() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).createNewFolder(CURRENT_FOLDER_NAME);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexDiskPage.checkFolderCreated(CURRENT_FOLDER_NAME), "Folder creation failed");
        softAssert.assertTrue(yandexDiskPage.visitCreatedFolder(CURRENT_FOLDER_NAME), "Folder visit failed");
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void createAndRenameNewFile() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver)
                .createNewFileInFolder(CURRENT_FOLDER_NAME, CURRENT_FILE_NAME, INPUT_TEXT);
        Assert.assertTrue(yandexDiskPage.checkCreatedFileExistsAndHasProperContent(
                CURRENT_FOLDER_NAME, CURRENT_FILE_NAME, INPUT_TEXT),
                "New file creation failed"
        );
    }

    @Test(priority = 6)
    public void removeFileTotrash() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).moveFileToTrash("temp_folder", "tempfile");
        Assert.assertTrue(yandexDiskPage.checkFileInTrash("tempfile"), "File was not removed");
    }

    @Test(priority = 7)
    public void remofeFileFromTrash() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).deleteFileCompletly("tempfile");
        Assert.assertFalse(yandexDiskPage.checkFileInTrash("tempfile"), "File was not deleted from Trash");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        webDriver.quit();
        webDriver = null;
    }
}
