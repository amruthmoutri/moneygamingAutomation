package com.moneygaming_gameaccount.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.moneygaming_gameaccount.cucumber"},
        features = {"classpath:features"},
        monochrome = true,
        dryRun = false
)
public class RegressionRunner {
}
