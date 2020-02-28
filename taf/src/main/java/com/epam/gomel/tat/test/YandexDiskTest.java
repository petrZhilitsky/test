package com.epam.gomel.tat.test;

import com.epam.gomel.tat.framework.bo.Folder;
import com.epam.gomel.tat.framework.bo.User;
import com.epam.gomel.tat.framework.bo.WordDocument;
import com.epam.gomel.tat.yandex_disk.service.AccountService;
import com.epam.gomel.tat.yandex_disk.service.LogInService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.epam.gomel.tat.framework.ui.Browser;
import com.epam.gomel.tat.framework.utils.TestListener;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class YandexDiskTest {
    private User user = new User();
    private LogInService logInService = new LogInService();
    private AccountService accountService = new AccountService();
    private Folder folder = new Folder();
    private WordDocument document = new WordDocument();
    private Folder folderToDelete = new Folder();

    @BeforeClass
    public void logInUser() {
        logInService.logIn(user);
    }

    @Test
    public void checkLoggedInYandexDiskAccount() {
        assertTrue(accountService.isLoggedIn(user.getLogin()), "User logging in failed");
    }

    @Test
    public void checkNavigationMenuItems() {
        assertTrue(accountService.isMainMenuItemsWorksCorrect(),"Main Menu items navigation failed");
    }

    @Test
    public void checkCreateAndVisitNewFolder() {
        accountService.createNewFolder(folder);
        assertTrue(accountService.isFolderCreated(folder), "Folder creating failed");
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewFolder"})
    public void checkCreateAndVisitNewWordDocument() {
        accountService.createNewWordFile(folder, document);
        assertTrue(accountService.isWordFileCreatedAndHasProperContent(folder, document), "File creating and editing failed");
    }

    @Test(dependsOnMethods = {"checkCreateAndVisitNewWordDocument"})
    public void checkMoveFolderToTrash() {
        accountService.createNewFolder(folderToDelete);
        accountService.moveFolderToTrash(folderToDelete);
        assertTrue(accountService.isFolderInTrash(folderToDelete), "Folder moving to trash failed");
    }

    @Test(dependsOnMethods = {"checkMoveFolderToTrash"})
    public void checkRemoveFolderFromTrash() {
        accountService.removeFolderFromTrash(folderToDelete);
        assertFalse(accountService.isFolderInTrashNoWait(folderToDelete), "Folder removing failed");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
