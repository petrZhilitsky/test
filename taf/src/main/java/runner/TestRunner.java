package runner;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws IOException {
        TestNG testNG = new TestNG(true);
        Parser parser = new Parser("src/main/resources/testng.xml");
        List<XmlSuite> suite = parser.parseToList();
        testNG.setXmlSuites(suite);
        testNG.run();
    }
}
