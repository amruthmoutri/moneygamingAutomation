package com.moneygaming_gameaccount.cucumber.step_definitions;

import com.moneygaming_gameaccount.config.Config;
import com.moneygaming_gameaccount.selenium.page.base.HomePage;
import com.moneygaming_gameaccount.selenium.page.base.JoinNowPage;
import com.moneygaming_gameaccount.selenium.webdriver.BrowserDriver;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageStepDefs implements En {

    private WebDriver driver = BrowserDriver.getDriver();
    private HomePage homePage = new HomePage(driver);
    private JoinNowPage joinNow = null;

    public HomePageStepDefs() throws IOException {

        Given("^user is on home page$", ()->{
            assertThat("User is not on homepage", homePage.validateUserIsOnHomePage(), equalTo(true));
        });

        When("^user clicks on join now button to display sign up page$", () ->{
            joinNow = homePage.clickJoinNowButton();
        });

        And("^join Now button is present on homepage$", ()->{
            assertThat("Join now button is not displayed ",
                    homePage.validateJoinNowButtonIsDisplayed(), is(true));
        });
    }
}
