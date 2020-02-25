package test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import bo.Folder;
import bo.User;
import bo.WordDocument;
import utils.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.Browser;
import yandex_disk.service.AccountService;
import yandex_disk.service.LogInService;

@Listeners({TestListener.class})
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
        assertTrue(accountService.isLoggedIn(user.getLogin()));
    }

    @Test
    public void checkNavigationMenuItems() {
        assertTrue(accountService.isMainMenuItemsWorksCorrect());
    }

    @Test
    public void checkCreateAndVisitNewFolder() {
        accountService.createNewFolder(folder);
        assertTrue(accountService.isFolderCreated(folder));
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewFolder"})
    public void checkCreateAndVisitNewWordDocument() {
        accountService.createNewWordFile(folder, document);
        assertTrue(accountService.isWordFileCreatedAndHasProperContent(folder, document));
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewWordDocument"})
    public void checkMoveFolderToTrash() {
        accountService.createNewFolder(folderToDelete);
        accountService.moveFolderToTrash(folderToDelete);
        assertTrue(accountService.isFolderInTrash(folderToDelete));
    }

    @Test(dependsOnMethods = {"checkMoveFolderToTrash"})
    public void checkRemoveFolderFromTrash() {
        accountService.removeFolderFromTrash(folderToDelete);
        assertFalse(accountService.isFolderInTrashNoWait(folderToDelete));
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
