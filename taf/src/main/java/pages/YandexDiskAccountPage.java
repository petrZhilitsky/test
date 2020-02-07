package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class YandexDiskAccountPage {
    private WebDriver webDriver;

    //navigation sidebar menu
    private By linkRecent = By.xpath("//a[@href='/client/recent']");
    private By linkFiles = By.xpath("//a[@id='/disk']");
    private By linkPhoto = By.xpath("//a[@href='/client/photo']");
    private By linkShared = By.xpath("(//a[@href='/client/journal']/preceding::*)[last()]");
    private By linkHistory = By.xpath("//a[@href='/client/journal']");
    private By linkArchive = By.xpath("//a[@href='/client/mail']");
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
    private By inputFindText = By.xpath("//input[contains(@id,'TextField')]");
    private By buttonSaveFileName = By.xpath("//button[@id='WACDialogActionButton']");
    private By checkChangesSaved = By.xpath("//span[@id='BreadcrumbSaveStatus' and text()='Сохранено в Yandex']");
    //delete file
    private By buttonDeleteFile = By.xpath("(//div[@class='resources-action-bar__side-right']//button[@type='button'])[8]");
    private By buttonDeleteFromTrash = By.xpath("(//div[@class='resources-action-bar__side-right']//button[@type='button'])[3]");

    public YandexDiskAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public YandexDiskAccountPage navigateRecentItem() {
        webDriver.findElement(linkRecent).click();
        return this;
    }

    public YandexDiskAccountPage navigateFilesItem() {
        webDriver.findElement(linkFiles).click();
        return this;
    }

    public YandexDiskAccountPage navigatePhotoItem() {
        webDriver.findElement(linkPhoto).click();
        return this;
    }

    public YandexDiskAccountPage navigateSharedItem() {
        webDriver.findElement(linkShared).click();
        return this;
    }

    public YandexDiskAccountPage navigateHistoryItem() {
        webDriver.findElement(linkHistory).click();
        return this;
    }

    public YandexDiskAccountPage navigateArchiveItem() {
        webDriver.findElement(linkArchive).click();
        return this;
    }

    public YandexDiskAccountPage navigateTrashItem() {
        webDriver.findElement(linkTrash).click();
        return this;
    }

    public YandexDiskAccountPage createNewFolder(String folderName) {
        webDriver.findElement(buttonCreateNew).click();
        webDriver.findElement(createNewFolder).click();
        webDriver.findElement(inputFolderName).sendKeys(Keys.CONTROL + "a");
        webDriver.findElement(inputFolderName).sendKeys(Keys.DELETE);
        webDriver.findElement(inputFolderName).sendKeys(folderName);
        webDriver.findElement(buttonSaveFolder).click();
        return this;
    }

    public YandexDiskAccountPage goToFolder(String folderName) {
        Actions actions = new Actions(webDriver);
        navigateFilesItem();
        actions.doubleClick(webDriver
                .findElement(By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", folderName)
                ))).perform();
        return this;
    }

    public YandexDiskAccountPage createNewFileInFolder(String folderName, String fileName, String inputText) {
        goToFolder(folderName);
        webDriver.findElement(buttonCreateNew).click();
        webDriver.findElement(createNewWordFile).click();
        switchChromeTabs(1);
        webDriver.switchTo().frame(0);
        new WebDriverWait(webDriver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(openFileMenu));
        webDriver.findElement(inputTextToFile).click();
        webDriver.findElement(openFileMenu).click();
        webDriver.findElement(renameFile).click();
        webDriver.findElement(inputFileName).sendKeys(Keys.CONTROL + "a");
        webDriver.findElement(inputFileName).sendKeys(Keys.DELETE);
        webDriver.findElement(inputFileName).sendKeys(fileName);
        webDriver.findElement(buttonSaveFileName).click();
        webDriver.findElement(inputTextToFile).sendKeys(inputText);
        webDriver.findElement(inputTextToFile).click();
        new WebDriverWait(webDriver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(checkChangesSaved));
        webDriver.close();
        switchChromeTabs(0);
        return this;
    }

    public YandexDiskAccountPage moveFileToTrash(String folderName, String fileName) {
        navigateFilesItem();
        goToFolder(folderName);
        webDriver.findElement(By.xpath(
                String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", fileName)
        )).click();
        new WebDriverWait(webDriver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonDeleteFile)).click();
        return this;
    }

    public YandexDiskAccountPage deleteFileCompletly(String fileName) {
        navigateTrashItem();
        webDriver.findElement(By.xpath(
                String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", fileName)
        )).click();
        new WebDriverWait(webDriver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonDeleteFromTrash)).click();
        return this;
    }

    private YandexDiskAccountPage switchChromeTabs(int tabNumber) {
        ArrayList<String> chromeTabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(chromeTabs.get(tabNumber));
        return this;
    }

    public boolean checkMainMenuItems() {
        navigateRecentItem();
        boolean checkRecentURL = webDriver.getCurrentUrl().contains("/client/recent");
        navigateFilesItem();
        boolean checkFilesURL = webDriver.getCurrentUrl().contains("/client/disk");
        navigatePhotoItem();
        boolean checkPhotoURL = webDriver.getCurrentUrl().contains("/client/photo");
        navigateSharedItem();
        boolean checkSharedURL = (webDriver.getCurrentUrl().contains("/client/shared")
                || webDriver.getCurrentUrl().contains("/client/published"));
        navigateHistoryItem();
        boolean checkHistoryURL = webDriver.getCurrentUrl().contains("/client/journal");
        navigateArchiveItem();
        boolean checkArchiveURL = webDriver.getCurrentUrl().contains("/client/mail");
        navigateTrashItem();
        boolean checkTrashURL = webDriver.getCurrentUrl().contains("/client/trash");

        return (checkRecentURL && checkFilesURL && checkPhotoURL && checkSharedURL && checkHistoryURL
                && checkArchiveURL && checkTrashURL);
    }

    public boolean checkFolderCreated(String folderName) {
        navigateFilesItem();
        return !new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", folderName)
                ))).isEmpty();
    }

    public boolean visitCreatedFolder(String folderName) {
        goToFolder(folderName);
        return !new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                        String.format("//h1[@title='%1$s']", folderName))
                )).isEmpty();
    }

    public boolean checkCreatedFileExists(String folderName, String fileName) {
        navigateFilesItem();
        goToFolder(folderName);
        return !new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                        String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", fileName))
                )).isEmpty();
    }

    public boolean checkCreatedFileHasProperContent(String folderName, String fileName, String inputText) {
        navigateFilesItem();
        goToFolder(folderName);
        Actions actions = new Actions(webDriver);
        actions.doubleClick(webDriver.findElement(By.xpath(
                String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", fileName)
        ))).perform();
        switchChromeTabs(1);
        webDriver.switchTo().frame(0);

        new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfElementLocated(inputTextToFile)
        ).sendKeys(Keys.CONTROL + "f");

        new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfElementLocated(inputFindText)
        ).sendKeys(inputText);

        return !new WebDriverWait(webDriver, 20).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(String.format("//*[text()='%1$s']", inputText))
                )).isEmpty();
    }

    public boolean checkFileInTrash(String fileName) {
        navigateTrashItem();
        return !webDriver.findElements(By.xpath(
                String.format("//div[@class='listing__items']//*[contains(text(),'%1$s') or contains(@title,'%1$s')]", fileName)
        )).isEmpty();
    }
}
