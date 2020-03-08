package com.epam.gomel.tat.framework.runner;

import com.epam.gomel.tat.framework.loger.Log;
import org.apache.log4j.PropertyConfigurator;
import org.testng.TestNG;

import java.util.Collections;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        Log.info("Set properties");
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
        Log.info("Set test suite");
        testNG.setTestSuites(Collections.singletonList("src/main/resources/testng-all.xml"));
        Log.info("Start test");
        testNG.run();
    }
}
