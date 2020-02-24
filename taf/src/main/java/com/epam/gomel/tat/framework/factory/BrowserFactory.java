package com.epam.gomel.tat.framework.factory;

import com.epam.gomel.tat.framework.runner.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
            default:
                System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriver());
                webDriver = new ChromeDriver();
                break;
        }
        return webDriver;
    }
}
