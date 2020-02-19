package yandex_disk.service;

import bo.Folder;
import bo.WordDocument;
import loger.Log;
import yandex_disk.pages.AccountPage;

public class AccountService {
    public void createNewFolder(Folder folder) {
        Log.debug("Creating folder " + folder.getName());
        new AccountPage()
                .navigateFilesItem()
                .clickCreateNew()
                .clickNewFolder()
                .renameFolder(folder.getName())
                .saveNewFolder();
    }

    public void createNewWordFile(Folder folder, WordDocument document) {
        Log.debug("Creating document " + document.getName());
        new AccountPage()
                .navigateFilesItem()
                .openObject(folder.getName())
                .clickCreateNew()
                .clickNewWordFile()
                .switchToAnotherTab()
                .switchToZeroFrame()
                .waitForWordEditorReady()
                .typeTextToWordFile(document.getText())
                .renameWordFile(document.getName())
                .waitForFileChangesSaved()
                .closeWordEditor()
                .switchToFirstTab();
    }

    public void moveFolderToTrash(Folder folder) {
        Log.debug("Moving folder " + folder.getName() + " to trash");
        new AccountPage()
                .navigateFilesItem()
                .chooseObject(folder.getName())
                .clickMoveToTrash();
    }

    public void removeFolderFromTrash(Folder folder) {
        Log.debug("Removing folder " + folder.getName() + " from trash");
        new AccountPage()
                .navigateTrashItem()
                .chooseObject(folder.getName())
                .clickRemoveFromTrash();
    }

    public boolean isMainMenuItemsWorksCorrect() {
        Log.debug("Checking main menu items");
        return new AccountPage()
                .isMainMenuItemsWorksCorrect();
    }

    public boolean isLoggedIn(String login) {
        Log.debug("Checking user " + login + " logged in");
        return new AccountPage().logInCheck(login);
    }

    public boolean isFolderCreated(Folder folder) {
        Log.debug("Checking folder " + folder.getName() + " creation");
        return new AccountPage()
                .navigateFilesItem()
                .checkObjectExists(folder.getName());
    }

    public boolean isWordFileCreatedAndHasProperContent(Folder folder, WordDocument document) {
        Log.debug("Checking file " + document.getName() + " creation and content");
        boolean isFileCreated = new AccountPage()
                .navigateFilesItem()
                .openObject(folder.getName())
                .checkObjectExists(document.getName());
        boolean isFileHasProperText = new AccountPage()
                .openObject(document.getName())
                .switchToAnotherTab()
                .switchToZeroFrame()
                .waitForWordEditorReady()
                .checkFileHasProperContent(document.getText());
        new AccountPage()
                .closeWordEditor()
                .switchToFirstTab();
        return isFileCreated && isFileHasProperText;
    }

    public boolean isFolderInTrash(Folder folder) {
        Log.debug("Checking folder " + folder.getName() + " in trash");
        return new AccountPage()
                .navigateTrashItem()
                .checkObjectExists(folder.getName());
    }

    public boolean isFolderInTrashNoWait(Folder folder) {
        Log.debug("Checking folder " + folder.getName() + " removed");
        return new AccountPage()
                .navigateTrashItem()
                .checkObjectExistsNoWait(folder.getName());
    }
}
