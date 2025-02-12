package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/api.feature"},
        glue = "stepdefinitions",
        plugin = {"pretty", "html:target/reports/RestAssuredCucumberReport.html"},
        dryRun = false,
        monochrome = true,
        tags= "@messages"
)
public class TestRunner {
}
