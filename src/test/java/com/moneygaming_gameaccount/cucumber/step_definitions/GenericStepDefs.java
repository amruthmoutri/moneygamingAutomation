package com.moneygaming_gameaccount.cucumber.step_definitions;

import com.moneygaming_gameaccount.selenium.page.base.BasePage;
import com.moneygaming_gameaccount.selenium.webdriver.BrowserDriver;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class GenericStepDefs implements En {

    private WebDriver driver = BrowserDriver.getDriver();
    private BasePage genericPage;

    public GenericStepDefs() throws IOException {
        genericPage = new BasePage(driver);

        When("^I close current window$", () -> driver.quit());

        Then("^I expect page title to be \"([^\"]*)\"$", (String title) ->
                assertThat("Page title incorrect.", genericPage.getPageTitle(), equalTo(title)));

        Then("^I expect page url to be $", (String url) -> {
            assertThat("User Navigated to wrong page ", genericPage.getPageUrl(), equalTo(url));
        });
    }

}
