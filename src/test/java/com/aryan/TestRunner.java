package com.aryan;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources",  // Path to your feature files
        glue = {"com.aryan"},                      // Step definitions package
        plugin = {"pretty", "html:target/cucumber-reports.html"},  // Plugins for reports
        monochrome = true,  // Makes console output readable
        tags = "@smoke"  // Optional: specify tags for which tests to run
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // This class will run your Cucumber tests using TestNG
}
