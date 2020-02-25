package com.epam.gomel.tat.framework.runner;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.epam.gomel.tat.framework.enums.BrowserType;
import com.epam.gomel.tat.framework.enums.SuiteType;
import org.apache.log4j.xml.DOMConfigurator;

public class Parameters {
    private static Parameters instance;

    @Parameter(names = "--help", help = false, description = "Help")
    private boolean help;

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", converter = BrowserTypeConverter.class, required = true)
    private BrowserType browserType = BrowserType.CHROME;

    @Parameter(names = {"--firefox", "-f"}, description = "Path to GeckoDriver")
    private String geckoDriver = "src/main/resources/geckodriver.exe";

    @Parameter(names = {"--chrome", "-c"}, description = "Path to ChromeDriver")
    private String chromeDriver = "src/main/resources/chromedriver.exe";

    @Parameter(names = {"--suite", "-s"}, description = "Browser type", converter = SuiteTypeConverter.class, required = true)
    private SuiteType suiteType = SuiteType.ALL;

    @Parameter(names = {"--all", "-a"}, description = "Path to All test suite")
    private String allSuite = "src/main/resources/testng-all.xml";

    @Parameter(names = {"--parallel", "-pa"}, description = "Path to Parallel test suite")
    private String parallelSuite = "src/main/resources/testng-parallel.xml";

    @Parameter(names = {"--smoke", "-sm"}, description = "Path to Smoke test suite")
    private String smokeSuite = "src/main/resources/testng-smoke.xml";

    @Parameter(names = {"--properties", "-pr"}, description = "Path to log4j.xml")
    private String properties = "src/main/resources/log4j.xml";

    private Parameters() {
    }

    public static synchronized Parameters instance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    public boolean isHelp() {
        return help;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public String getGeckoDriver() {
        return geckoDriver;
    }

    public String getProperties() {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        return properties;
    }

    public SuiteType getSuiteType() {
        return suiteType;
    }

    public String getAllSuite() {
        return allSuite;
    }

    public String getParallelSuite() {
        return parallelSuite;
    }

    public String getSmokeSuite() {
        return smokeSuite;
    }

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }

    public static class SuiteTypeConverter implements IStringConverter<SuiteType> {
        public SuiteType convert(String s) {
            return SuiteType.valueOf(s.toUpperCase());
        }
    }
}
