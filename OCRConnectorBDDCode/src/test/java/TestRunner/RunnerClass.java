package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\Features\\ExecuteDCommand.feature",glue = "StepDefinitions"
        )
public class RunnerClass extends AbstractTestNGCucumberTests {
}
