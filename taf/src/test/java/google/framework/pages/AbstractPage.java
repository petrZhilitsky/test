package google.framework.pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver webDriver;

    protected abstract AbstractPage openPage();

    protected final int WAIT_TIMEOUT_SECONDS = 60;

    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
