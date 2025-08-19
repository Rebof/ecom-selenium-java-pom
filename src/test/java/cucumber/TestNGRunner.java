package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber", // path to your feature files
    glue = "ecom.stepDefinitions",             // package containing step definitions & hooks
    tags = "@Filters",                            // run only scenarios with this tag
    monochrome = true,
    plugin = {
        "pretty",                              // console-friendly output
        "html:target/cucumber-reports/report.html",
    }
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
    
}
