package com.epam.gomel.tat.yandex_disk.pages;

import com.epam.gomel.tat.framework.factory.Configuration;
import org.openqa.selenium.By;

import static com.epam.gomel.tat.framework.ui.Browser.getInstance;

public class LogInPage {
    private By logInMainPageButton = By.xpath("//a[@class='button button_login header__login-link']");
    private By loginInput = By.xpath("//input[@id='passp-field-login']");
    private By passwordInput = By.xpath("//input[@id='passp-field-passwd']");
    private By confirmButton = By.xpath("//span/parent::button[@type='submit']");

    public LogInPage openPage() {
        getInstance().get(Configuration.YANDEX_URL_LOG_IN);
        return this;
    }

    public LogInPage openCredentialsInput() {
        getInstance().click(logInMainPageButton);
        return this;
    }

    public LogInPage inputLogin(String login) {
        getInstance().type(loginInput, login);
        return this;
    }

    public LogInPage inputPassword(String password) {
        getInstance().type(passwordInput, password);
        return this;
    }

    public LogInPage clickConfirm() {
        getInstance().click(confirmButton);
        return this;
    }
}
