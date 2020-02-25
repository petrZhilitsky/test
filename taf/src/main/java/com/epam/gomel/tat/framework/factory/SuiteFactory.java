package com.epam.gomel.tat.framework.factory;

import com.epam.gomel.tat.framework.runner.Parameters;
import org.testng.TestNG;

import java.util.Collections;

public class SuiteFactory {
    private SuiteFactory() {
    }

    public static TestNG getSuite() {
        TestNG testNG = new TestNG();
        switch (Parameters.instance().getSuiteType()) {
            case ALL:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getAllSuite()));
                break;
            case PARALLEL:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getParallelSuite()));
                break;
            case SMOKE:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getSmokeSuite()));
                break;
            default:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getAllSuite()));
                break;
        }
        return testNG;
    }
}
