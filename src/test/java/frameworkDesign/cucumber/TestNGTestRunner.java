package frameworkDesign.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		features = "src/test/java/frameworkDesign/cucumber", 
		glue = "frameworkDesign.stepDefinitions", 
		tags = "@ErrorValidation",
		monochrome = true,
		plugin = {"html:reports/cucumber-reports/cucumber.html"},
		dryRun = false
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
