import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features", glue="stepsDefinition",
plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber.json"})
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
