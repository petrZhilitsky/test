package com.epam.gomel.tat.framework.factory;

import com.epam.gomel.tat.framework.loger.Log;
import com.epam.gomel.tat.framework.runner.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
    private BrowserFactory() {
    }

    public static WebDriver getBrowser() {
        WebDriver webDriver;
        switch (Parameters.instance().getBrowserType()) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriver());
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webDriver.gecko.driver", Parameters.instance().getGeckoDriver());
                webDriver = new FirefoxDriver();
                break;
            case REMOTE_CHROME:
                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    Log.error("Remote WebDriver failed, starting Chrome driver");
                    webDriver = new ChromeDriver();
                }
                break;
            case REMOTE_FIREFOX:
                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    Log.error("Remote WebDriver failed, starting Firefox driver");
                    webDriver = new FirefoxDriver();
                }
                break;
            default:
                System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriver());
                webDriver = new ChromeDriver();
                break;
        }
        return webDriver;
    }
}
