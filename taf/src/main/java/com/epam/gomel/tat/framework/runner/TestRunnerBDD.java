package com.epam.gomel.tat.framework.runner;

import com.epam.gomel.tat.framework.ui.Browser;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/main/resources/feature",
        glue = "com.epam.gomel.tat.test.steps",
        tags = "@All",
        plugin = {
                "pretty", "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        }
)

public class TestRunnerBDD {
    @AfterClass
    public static void closeBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
