package com.epam.gomel.tat.test.steps;

import com.epam.gomel.tat.framework.bo.Folder;
import com.epam.gomel.tat.framework.bo.User;
import com.epam.gomel.tat.framework.bo.WordDocument;
import com.epam.gomel.tat.yandex_disk.pages.AccountPage;
import com.epam.gomel.tat.yandex_disk.pages.LogInPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.gomel.tat.framework.ui.Browser.getInstance;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class YandexDiskTestSteps {
    private LogInPage logInPage = new LogInPage();
    private AccountPage accountPage = new AccountPage();
    private User user = new User();
    private Folder folder = new Folder();
    private WordDocument document = new WordDocument();

    @Given("^user (?:is on|gets) the (?:log in|login) page$")
    public void userIsOnTheLogInPage() {
        logInPage.openPage();
    }

    @When("^user clicks (?:log in|sign in|login) button$")
    public void userClicksLogInButton() {
        logInPage.openCredentialsInput();
    }

    @When("^user (?:enters|types) login (.*)$")
    public void userEntersLogin(String login) {
        logInPage.inputLogin(login);
    }

    @And("^user clicks (?:confirm|submit) button$")
    public void userClicksConfirmButton() {
        logInPage.clickConfirm();
    }

    @And("^user (?:enters|types) password (.*)$")
    public void userEntersPassword(String password) {
        logInPage.inputPassword(password);
    }

    @Then("^user (?:reaches|reaches the|is on the) Yandex Disk (?:account|main) page$")
    public void userReachesYandexDiskAccountPage() {
        assertTrue("User logging in failed", accountPage.logInCheck(user.getLogin()));
    }

    @When("^user (?:navigates|goes to) the Recent item$")
    public void userNavigatesTheRecentItem() {
        accountPage.navigateRecentItem();
    }

    @And("^user (?:navigates|goes to) the Files item$")
    public void userNavigatesTheFilesItem() {
        accountPage.navigateFilesItem();
    }

    @And("^user (?:navigates|goes to) the Photo item$")
    public void userNavigatesThePhotoItem() {
        accountPage.navigatePhotoItem();
    }

    @And("^user (?:navigates|goes to) the Shared item$")
    public void userNavigatesTheSharedItem() {
        accountPage.navigateSharedItem();
    }

    @And("^user (?:navigates|goes to) the Journal item$")
    public void userNavigatesTheJournalItem() {
        accountPage.navigateHistoryItem();
    }

    @And("^user (?:navigates|goes to) the Archive item$")
    public void userNavigatesTheArchiveItem() {
        accountPage.navigateArchiveItem();
    }

    @And("^user (?:navigates|goes to) the Trash item$")
    public void userNavigatesTheTrashItem() {
        accountPage.navigateTrashItem();
    }

    @Then("^user (?:gets|is on) the (.*) Main Menu (?:item|page)$")
    public void userGetsThePage(String currentPage) {
        assertTrue("Main Menu items navigation failed", getInstance().getCurrentUrl().contains(currentPage)
                || getInstance().getCurrentUrl().contains("/client/published"));
    }

    @And("^user clicks Create button$")
    public void userClicksCreateButton() {
        accountPage.clickCreateNew();
    }

    @And("^user clicks Folder button$")
    public void userClicksFolderButton() {
        accountPage.clickNewFolder();
    }

    @And("^user (?:gives|types) name to folder$")
    public void userGivesNameToFolder() {
        accountPage.renameFolder(folder.getName());
    }

    @And("^user clicks Save button$")
    public void userClicksSaveButton() {
        accountPage.saveNewFolder();
    }

    @Then("^user (?:sees|can see) new folder in (?:Files|Trash) item$")
    public void userSeesNewFolder() {
        assertTrue("Folder doesn't exist", accountPage.checkObjectExists(folder.getName()));
    }

    @And("^user (?:opens|visits|enters) new folder$")
    public void userOpensNewFolder() {
        accountPage.openObject(folder.getName());
    }

    @And("^user clicks (?:Word|new Word|new) File button$")
    public void userClicksWordFileButton() {
        accountPage.clickNewWordFile();
    }

    @And("^user (?:navigates|goes to|switches to) Word editor tab$")
    public void userNavigatesWordEditorTab() {
        accountPage
                .switchToAnotherTab()
                .switchToZeroFrame()
                .waitForWordEditorReady();
    }

    @And("^user (?:enters|types) text in new Word file$")
    public void userTypesTextInNewWordFile() {
        accountPage.typeTextToWordFile(document.getText());
    }

    @And("^user (?:enters|types|renames) new Word file(?: name|)$")
    public void userRenamesNewWordFile() {
        accountPage
                .renameWordFile(document.getName())
                .waitForFileChangesSaved();
    }

    @And("^user (?:closes|exits) Word editor$")
    public void userClosesWordEditor() {
        accountPage
                .closeWordEditor()
                .switchToFirstTab();
    }

    @Then("^user (?:sees|can see) new Word file(?: in Files item)$")
    public void userSeesNewWordFileInFilesItem() {
        assertTrue("File doesn't exist", accountPage.checkObjectExists(document.getName()));
    }

    @And("^user opens new Word file$")
    public void userOpensNewWordFile() {
        accountPage.openObject(document.getName());
    }

    @And("^user (?:sees|can see) text of new Word file$")
    public void userSeesTextOfNewWordFile() {
        assertTrue("File has wrong content", accountPage.checkFileHasProperContent(document.getText()));
    }

    @And("^user (?:chooses|marks) new folder$")
    public void userChoosesNewFolder() {
        accountPage.chooseObject(folder.getName());
    }

    @And("^user clicks Delete button$")
    public void userClicksDeleteButton() {
        accountPage.clickMoveToTrash();
    }

    @And("^user clicks Remove button$")
    public void userClicksRemoveButton() {
        accountPage.clickRemoveFromTrash();
    }

    @Then("^user (?:do not|don't|can't|can not) see new folder in Trash item$")
    public void userDoNotSeeNewFolderInTrashItem() {
        assertFalse("Folder removing failed", accountPage.checkObjectExistsNoWait(document.getName()));
    }
}
