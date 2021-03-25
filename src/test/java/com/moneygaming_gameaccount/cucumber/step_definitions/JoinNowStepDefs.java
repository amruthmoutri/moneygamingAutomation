package com.moneygaming_gameaccount.cucumber.step_definitions;

import com.moneygaming_gameaccount.selenium.page.base.JoinNowPage;
import com.moneygaming_gameaccount.selenium.webdriver.BrowserDriver;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JoinNowStepDefs implements En {

    private WebDriver driver = BrowserDriver.getDriver();
    private JoinNowPage joinNowStepDefs = new JoinNowPage(driver);

    public JoinNowStepDefs() throws IOException {

        Given("^I expect sign up page to have form labels$", () -> {
            assertThat("", joinNowStepDefs.verifyJoiNowHasAllFieldValues(), is(true));
        });

        When("^I click on Join now button$", () -> {
            joinNowStepDefs.clickJoinNowButton();
        });

        When("^I verify \"([^\"]*)\" appears under \"([^\"]*)\"$", (String mandatoryText, String fieldName) -> {
            assertThat(mandatoryText + " does not appear under " + fieldName,
                    joinNowStepDefs.verifyMandatoryTextAppearsUnderFields(mandatoryText, fieldName), is(true));
        });


        Then("^user enter details on sign up page$", (DataTable fields) -> {
            Map<String, String> values = fields.asMap(String.class, String.class);
            joinNowStepDefs.enterSignUpFieldValues(values);

        });

        Then("^user \"([^\"]*)\" \"([^\"]*)\" on sign in page and verify it's selected$", (String acceptOrUnaccept, String checkboxName) -> {
            assertThat(checkboxName + " is not selected",
                    joinNowStepDefs.isCheckBoxSelected
                            (joinNowStepDefs.checkORUncheckBox(acceptOrUnaccept, checkboxName)), is(true));
        });

        Then("^I expect terms and conditions on sign in page to not be selected$", () ->{
            assertThat("Terms and conditions are selected ", joinNowStepDefs.verifyTermAndConditionsIsSelected(), is(false));
        });
    }

}
