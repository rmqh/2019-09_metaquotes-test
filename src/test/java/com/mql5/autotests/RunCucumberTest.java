package com.mql5.autotests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue= {"com.mql5.autotests.stepdefs"},
        plugin = { "pretty", "html:target/cucumber-reports" }
)
public class RunCucumberTest {
}
