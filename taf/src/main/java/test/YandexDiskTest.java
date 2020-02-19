package test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import bo.Folder;
import bo.User;
import bo.WordDocument;
import loger.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.Browser;
import yandex_disk.service.AccountService;
import yandex_disk.service.LogInService;

public class YandexDiskTest {
    private User user = new User();
    private LogInService logInService = new LogInService();
    private AccountService accountService = new AccountService();
    private Folder folder = new Folder();
    private Folder folderToDelete = new Folder();
    private WordDocument document = new WordDocument();

    @BeforeClass
    public void logInUser() {
        logInService.logIn(user);
    }

    @Test
    public void checkLoggedInYandexDiskAccount() {
        Log.info("Start checkLoggedInYandexDiskAccount test");
        try {
            assertTrue(accountService.isLoggedIn(user.getLogin()));
        } catch (Throwable e) {
            Log.error("Logging in failed, " + e.getMessage());
        }
        Log.info("Finish checkLoggedInYandexDiskAccount test");
    }

    @Test
    public void checkNavigationMenuItems() {
        Log.info("Start checkNavigationMenuItems test");
        try {
            assertTrue(accountService.isMainMenuItemsWorksCorrect());
        } catch (Throwable e) {
            Log.error("Main Menu items incorrect, " + e.getMessage());
        }
        Log.info("Finish checkNavigationMenuItems test");
    }

    @Test
    public void checkCreateAndVisitNewFolder() {
        Log.info("Start checkCreateAndVisitNewFolder test");
        accountService.createNewFolder(folder);
        try {
            assertTrue(accountService.isFolderCreated(folder));
        } catch (Throwable e) {
            Log.error("Folder creation failed, " + e.getMessage());
        }
        Log.info("Finish checkCreateAndVisitNewFolder test");
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewFolder"})
    public void checkCreateAndVisitNewWordDocument() {
        Log.info("Start checkCreateAndVisitNewWordDocument test");
        accountService.createNewWordFile(folder, document);
        try {
            assertTrue(accountService.isWordFileCreatedAndHasProperContent(folder, document));
        } catch (Throwable e) {
            Log.error("File creation failed, " + e.getMessage());
        }
        Log.info("Finish checkCreateAndVisitNewWordDocument test");
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewWordDocument"})
    public void checkMoveFolderToTrash() {
        Log.info("Start checkMoveFolderToTrash test");
        accountService.createNewFolder(folderToDelete);
        accountService.moveFolderToTrash(folderToDelete);
        try {
            assertTrue(accountService.isFolderInTrash(folderToDelete));
        } catch (Throwable e) {
            Log.error("Folder moving to trash failed, " + e.getMessage());
        }
        Log.info("Finish checkMoveFolderToTrash test");
    }

    @Test(dependsOnMethods = {"checkMoveFolderToTrash"})
    public void checkRemoveFolderFromTrash() {
        Log.info("Start checkRemoveFolderFromTrash test");
        accountService.removeFolderFromTrash(folderToDelete);
        try {
            assertFalse(accountService.isFolderInTrashNoWait(folderToDelete));
        } catch (Throwable e) {
            Log.error("Folder removing failed, " + e.getMessage());
        }
        Log.info("Finish checkRemoveFolderFromTrash test");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
