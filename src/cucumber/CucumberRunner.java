package src.cucumber;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@Cucumber.Options(
		format = {"pretty", "json:target/"},
		features = {"src/cucumber/"}
		)
public class CucumberRunner {

}
