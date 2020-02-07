import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

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

    @Test
    public void logInYandexDiskAccountPositive() {
        YandexDiskMainPage mainPageLogIn = new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        assertTrue(mainPageLogIn.checkLoggedIn(LOGIN), "Log In failed");
    }

    @Test
    public void logInYandexDiskAccountNegative() {
        YandexDiskMainPage mainPageLogIn = new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD + "!");
        assertFalse(mainPageLogIn.checkLoggedIn(LOGIN), "Log In succeeded");
    }

    @Test
    public void checkNavigationMenuItems() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        assertTrue(new YandexDiskAccountPage(webDriver).checkMainMenuItems(), "Navigation URLs incorrect");
    }

    @Test
    public void createAndVisitNewFolder() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).createNewFolder(CURRENT_FOLDER_NAME);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexDiskPage.checkFolderCreated(CURRENT_FOLDER_NAME), "Folder creation failed");
        softAssert.assertTrue(yandexDiskPage.visitCreatedFolder(CURRENT_FOLDER_NAME), "Folder visit failed");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"createAndVisitNewFolder"})
    public void createAndRenameNewFile() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver)
                .createNewFileInFolder(CURRENT_FOLDER_NAME, CURRENT_FILE_NAME, INPUT_TEXT);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexDiskPage.checkCreatedFileExists(
                CURRENT_FOLDER_NAME, CURRENT_FILE_NAME), "New file creation failed"
        );
        softAssert.assertTrue(yandexDiskPage.checkCreatedFileHasProperContent(
                CURRENT_FOLDER_NAME, CURRENT_FILE_NAME, INPUT_TEXT),
                "New file creation failed"
        );
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"createAndVisitNewFolder", "createAndRenameNewFile"})
    public void removeFileToTrash() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).moveFileToTrash(CURRENT_FOLDER_NAME, CURRENT_FILE_NAME);
        assertTrue(yandexDiskPage.checkFileInTrash(CURRENT_FILE_NAME), "File was not removed");
    }

    @Test(dependsOnMethods = {"createAndVisitNewFolder", "createAndRenameNewFile", "removeFileToTrash"})
    public void remofeFileFromTrash() {
        new YandexDiskMainPage(webDriver).logInToAccount(LOGIN, PASSWORD);
        YandexDiskAccountPage yandexDiskPage = new YandexDiskAccountPage(webDriver).deleteFileCompletly(CURRENT_FILE_NAME);
        assertFalse(yandexDiskPage.checkFileInTrash(CURRENT_FILE_NAME), "File was not deleted from Trash");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        webDriver.quit();
        webDriver = null;
    }
}
