package ui;

import loger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Browser implements WrapsDriver {
    private static final int WAIT_TIMEOUT_SECONDS = 20;
    private static ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedWebDriver;

    private Browser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        wrappedWebDriver = new ChromeDriver();
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
        }
        return instance.get();
    }

    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.remove();
        }
    }

    public void get(String url) {
        Log.debug("Open url: " + url);
        wrappedWebDriver.get(url);
        makeScreenshot();
    }

    public String getCurrentWindowHandle() {
        return wrappedWebDriver.getWindowHandle();
    }

    public String getCurrentUrl() {
        Log.debug("Get current url");
        return wrappedWebDriver.getCurrentUrl();
    }

    public void click(By by) {
        Log.debug("Click " + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        element.click();
    }

    public void doubleClick(By by) {
        Log.debug("Double click " + by);
        Actions actions = new Actions(wrappedWebDriver);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        actions.doubleClick(element).perform();
    }

    public void type(By by, String keys) {
        Log.debug("Type " + keys + " in field" + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        element.sendKeys(keys);
    }

    public void clear(By by) {
        Log.debug("Clear field " + by);
        wrappedWebDriver.findElement(by).sendKeys(Keys.chord(Keys.LEFT_CONTROL + "a"));
    }

    public void switchToFrame(int index) {
        Log.debug("Switch to frame " + index);
        wrappedWebDriver.switchTo().frame(index);
    }

    public void switchToTab(String handle) {
        Log.debug("Switch to first tab");
        wrappedWebDriver.switchTo().window(handle);
    }

    public void switchToAnotherTab() {
        Log.debug("Switch to another tab");
        for (String winHandle : wrappedWebDriver.getWindowHandles()) {
            wrappedWebDriver.switchTo().window(winHandle);
        }
    }

    public boolean isVisible(By by) {
        try {
            waitForVisibilityOfElement(by);
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public boolean isVisibleNoWait(By by) {
        try {
            waitForVisibilityOfElement(by, 0);
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public WebElement waitForVisibilityOfElement(By by) {
        Log.debug("Wait for visibility of element " + by);
        return new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForVisibilityOfElement(By by, long timeout) {
        Log.debug("Wait for visibility of element " + by);
        return new WebDriverWait(wrappedWebDriver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void makeScreenshot() {
        File screenshotFile = new File("screenshots/" + System.currentTimeMillis() + "screenshot.png");
        try {
            File screenshotAsFile = ((TakesScreenshot) wrappedWebDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotAsFile, screenshotFile);
            Log.info("<a href=\"" + screenshotFile.getAbsolutePath() + "\" target=\"blank\">screenshot.file</a>");
        } catch (IOException e) {
            Log.error("Failed screenshot: " + e.getMessage());
        }
    }

    public void highlightBackground(WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) wrappedWebDriver);
        js.executeScript("arguments[0].style.backgroundColor = 'yellow'", element);
        makeScreenshot();
        js.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }
}
