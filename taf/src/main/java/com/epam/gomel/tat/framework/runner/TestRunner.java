package com.epam.gomel.tat.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.epam.gomel.tat.framework.factory.SuiteFactory;
import com.epam.gomel.tat.framework.loger.Log;
import org.apache.log4j.PropertyConfigurator;

public class TestRunner {
    public static void main(String[] args) {
        Log.info("Parse CLIs");
        parseCLI(args);
        Log.info("Set log4j.xml");
        PropertyConfigurator.configure(Parameters.instance().getProperties());
        Log.info("Start app");
        SuiteFactory.getSuite().run();
        Log.info("Finish app");
    }

    private static void parseCLI(String[] args) {
        Log.debug("Parsing CLIs using JCommander");
        JCommander jCommander = new JCommander(Parameters.instance());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            System.exit(1);
        }
        if (Parameters.instance().isHelp()) {
            jCommander.usage();
            System.exit(0);
        }
    }
}
