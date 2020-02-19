package yandex_disk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static ui.Browser.getInstance;

public class AccountPage {
    private By accountAvatarButton = By.xpath("//img[@class='user-pic__image']");
    //navigation sidebar menu
    private By linkRecent = By.xpath("//a[@href='/client/recent']");
    private By linkFiles = By.xpath("//a[@id='/disk']");
    private By linkPhoto = By.xpath("//a[@href='/client/photo']");
    private By linkShared = By.xpath("(//a[@href='/client/journal']/preceding::*)[last()]");
    private By linkHistory = By.xpath("//a[@href='/client/journal']");
    private By linkArchive = By.xpath("//a[@href='/client/attach']");
    private By linkTrash = By.xpath("//a[@id='/trash']");
    //elements to create new folder
    private By buttonCreateNew = By.xpath("//div[@class='sidebar__buttons']/span[2]/button");
    private By createNewFolder = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']/button[1]");
    private By inputFolderName = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private By buttonSaveFolder = By.xpath("//div[@class='confirmation-dialog__footer']/button");
    //elements to create new file
    private By createNewWordFile = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']/button[2]");
    private By inputTextToFile = By.xpath("//div[@id='WACViewPanel_EditingElement']");
    private By openFileMenu = By.xpath("//button[@data-automation-id='FileMenu']");
    private By renameFile = By.xpath("//a[@id='jbtnRenameDialog-Menu48']");
    private By inputFileName = By.xpath("//input[@id='txtDocumentName']");
    private By inputFindText = By.xpath("//input[@id='FindSearchBox']");
    private By buttonSaveFileName = By.xpath("//button[@id='WACDialogActionButton']");
    private By checkChangesSaved = By.xpath("//span[@id='BreadcrumbSaveStatus' and text()='Сохранено в Yandex']");
    private By closeEditorButton = By.xpath("//a[@id='btnjClose-Menu32']");
    //delete file
    private By buttonDeleteFile = By.xpath("(//div[@class='resources-action-bar__side-right']//button[@type='button'])[7]");
    private By buttonDeleteFromTrash = By.xpath("(//div[@class='resources-action-bar__side-right']//button[@type='button'])[3]");

    private static String currentWindowHandle;

    public AccountPage navigateRecentItem() {
        getInstance().click(linkRecent);
        return this;
    }

    public AccountPage navigateFilesItem() {
        getInstance().click(linkFiles);
        return this;
    }

    public AccountPage navigatePhotoItem() {
        getInstance().click(linkPhoto);
        return this;
    }

    public AccountPage navigateSharedItem() {
        getInstance().click(linkShared);
        return this;
    }

    public AccountPage navigateHistoryItem() {
        getInstance().click(linkHistory);
        return this;
    }

    public AccountPage navigateArchiveItem() {
        getInstance().click(linkArchive);
        return this;
    }

    public AccountPage navigateTrashItem() {
        getInstance().click(linkTrash);
        return this;
    }

    public AccountPage clickCreateNew() {
        getInstance().click(buttonCreateNew);
        return this;
    }

    public AccountPage clickNewFolder() {
        getInstance().click(createNewFolder);
        return this;
    }

    public AccountPage renameFolder(String folderName) {
        getInstance().clear(inputFolderName);
        getInstance().type(inputFolderName, folderName);
        return this;
    }

    public AccountPage saveNewFolder() {
        getInstance().click(buttonSaveFolder);
        return this;
    }

    public AccountPage openObject(String objectName) {
        getInstance().doubleClick(
                By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]",
                                objectName)
                )
        );
        return this;
    }

    public AccountPage clickNewWordFile() {
        getInstance().click(createNewWordFile);
        return this;
    }

    public AccountPage switchToAnotherTab() {
        currentWindowHandle = getInstance().getCurrentWindowHandle();
        getInstance().switchToAnotherTab();
        return this;
    }

    public AccountPage switchToFirstTab() {
        getInstance().switchToTab(currentWindowHandle);
        return this;
    }

    public AccountPage switchToZeroFrame() {
        getInstance().switchToFrame(0);
        return this;
    }

    public AccountPage waitForWordEditorReady() {
        getInstance().waitForVisibilityOfElement(openFileMenu);
        return this;
    }

    public AccountPage typeTextToWordFile(String text) {
        getInstance().click(inputTextToFile);
        getInstance().type(inputTextToFile, text);
        return this;
    }

    public AccountPage renameWordFile(String fileName) {
        getInstance().click(openFileMenu);
        getInstance().click(renameFile);
        getInstance().clear(inputFileName);
        getInstance().type(inputFileName, fileName);
        getInstance().click(buttonSaveFileName);
        return this;
    }

    public AccountPage waitForFileChangesSaved() {
        getInstance().waitForVisibilityOfElement(checkChangesSaved);
        return this;
    }

    public AccountPage closeWordEditor() {
        getInstance().click(openFileMenu);
        getInstance().click(closeEditorButton);
        return this;
    }

    public AccountPage chooseObject(String objectName) {
        getInstance().click(
                By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]",
                                objectName)
                )
        );
        return this;
    }

    public AccountPage clickMoveToTrash() {
        getInstance().click(buttonDeleteFile);
        return this;
    }

    public AccountPage clickRemoveFromTrash() {
        getInstance().click(buttonDeleteFromTrash);
        return this;
    }

    public boolean isMainMenuItemsWorksCorrect() {
        navigateRecentItem();
        boolean checkRecentURL = getInstance().getCurrentUrl().contains("/client/recent");
        navigateFilesItem();
        boolean checkFilesURL = getInstance().getCurrentUrl().contains("/client/disk");
        navigatePhotoItem();
        boolean checkPhotoURL = getInstance().getCurrentUrl().contains("/client/photo");
        navigateSharedItem();
        boolean checkSharedURL = (getInstance().getCurrentUrl().contains("/client/shared")
                || getInstance().getCurrentUrl().contains("/client/published"));
        navigateHistoryItem();
        boolean checkHistoryURL = getInstance().getCurrentUrl().contains("/client/journal");
        navigateArchiveItem();
        boolean checkArchiveURL = getInstance().getCurrentUrl().contains("/client/attach");
        navigateTrashItem();
        boolean checkTrashURL = getInstance().getCurrentUrl().contains("/client/trash");

        return (checkRecentURL && checkFilesURL && checkPhotoURL && checkSharedURL && checkHistoryURL
                && checkArchiveURL && checkTrashURL);
    }

    public boolean logInCheck(String login) {
        if (getInstance().isVisible(accountAvatarButton)) {
            getInstance().click(accountAvatarButton);
            return getInstance().isVisible(By.xpath("//div/span[@class='user-account__name' and text()='" + login + "']"));
        }
        return false;
    }

    public boolean checkObjectExists(String objectName) {
        return getInstance().isVisible(
                By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]",
                                objectName)
                )
        );
    }

    public boolean checkObjectExistsNoWait(String objectName) {
        return getInstance().isVisibleNoWait(
                By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]",
                                objectName)
                )
        );
    }

    public boolean checkFileHasProperContent(String fileText) {
        getInstance().click(inputTextToFile);
        getInstance().type(inputTextToFile, Keys.CONTROL + "f");
        getInstance().type(inputFindText, fileText);
        return getInstance().isVisible(
                By.xpath(
                        String.format("//*[text()='%1$s']",
                                fileText))
        );
    }
}
