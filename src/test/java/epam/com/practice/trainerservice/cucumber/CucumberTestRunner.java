package epam.com.practice.trainerservice.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/epam/com/practice/trainerservice/resources/cucumber/features",
        glue = "epam.com.practice.trainerservice.cucumber.steps",
        plugin = {"pretty", "json:target/cucumber.json"},
        monochrome = true
)
public class CucumberTestRunner {

}
