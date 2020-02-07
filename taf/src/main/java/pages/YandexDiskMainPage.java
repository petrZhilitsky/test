package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class YandexDiskMainPage {
    private static final String URL = "https://disk.yandex.com/";
    private WebDriver webDriver;
    private By logInMainPageButton = By.xpath("//a[@class='button button_login header__login-link']");
    private By loginInput = By.xpath("//input[@id='passp-field-login']");
    private By passwordInput = By.xpath("//input[@id='passp-field-passwd']");
    private By confirmButton = By.xpath("//span/parent::button[@type='submit']");

    private By errorCredentialsMessage = By.xpath("//div[@class='passp-form-field__error']");
    private By accountAvatarButton = By.xpath("//img[@class='user-pic__image']");

    public YandexDiskMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public YandexDiskMainPage logInToAccount(String login, String password) {
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.get(URL);
        webDriver.findElement(logInMainPageButton).click();
        webDriver.findElement(loginInput).sendKeys(login);
        webDriver.findElement(confirmButton).click();
        if (!webDriver.findElements(errorCredentialsMessage).isEmpty()) {
            return this;
        }
        webDriver.findElement(passwordInput).sendKeys(password);
        webDriver.findElement(confirmButton).click();
        if (!webDriver.findElements(errorCredentialsMessage).isEmpty()) {
            return this;
        }
        return this;
    }

    public boolean checkLoggedIn(String login) {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (!webDriver.findElements(accountAvatarButton).isEmpty()) {
            webDriver.findElement(accountAvatarButton).click();
            return !webDriver.findElements(By.xpath(
                    String.format("//div/span[@class='user-account__name' and text()='%1$s']", login))).isEmpty();
        }
        return false;
    }
}
