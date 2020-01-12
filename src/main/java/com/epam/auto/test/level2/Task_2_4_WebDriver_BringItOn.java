package com.epam.auto.test.level2;

import com.epam.auto.test.level2.pages.PastebinMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Task_2_4_WebDriver_BringItOn {

    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        new PastebinMainPage(webDriver).createNewPaste();
        TimeUnit.SECONDS.sleep(5);
        webDriver.quit();
    }
}
