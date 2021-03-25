package com.moneygaming_gameaccount.selenium.page.base;

import com.moneygaming_gameaccount.exceptions.ButtonNotAvailableException;
import com.moneygaming_gameaccount.exceptions.FieldNotAvailableException;
import com.moneygaming_gameaccount.exceptions.OptionNotAvailableException;
import com.moneygaming_gameaccount.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;


public class JoinNowPage extends BasePage {

    private static String signUpFormFieldsLegend = "legend";
    private static Logger log = Logger.getLogger(JoinNowPage.class);
    private static By regestrationContent = By.cssSelector("div[class='content']");
    private static By regestrationJourneyImage = By.cssSelector("img[src$='registrationJourney1.png']");
    private static By signUpFormFields = By.cssSelector("fieldset:not([class='underlay'])");
    private static By dobGenericOptions = By.cssSelector("[class^='dob']");
    private static By surName = By.name("map(lastName)");
    private static By emailAddress = By.name("map(email)");
    private static By telephone = By.name("map(phone)");
    private static By mobile = By.name("map(mobile)");
    private static By addressLineOne = By.name("map(address1)");
    private static By addressCity = By.name("map(addressCity)");
    private static By county = By.name("map(stateProv)");
    private static By postCode = By.name("map(postCode)");
    private static By chooseUsername = By.name("map(userName)");
    private static By passwordConfirm = By.name("map(passwordConfirm)");
    private static By securityQuestionOneAnswer = By.name("map(securityAnswerOne)");
    private static By securityQuestionTwoAnswer = By.name("map(securityAnswerTwo)");
    private static By currencyList = By.name("map(currency)");
    private static By termsAndConditions = By.name("map(terms)");
    private static By securityQuestionOne = By.id("securityQuestionOne");
    private static By securityQuestionTwo = By.id("securityQuestionTwo");
    private static By password = By.id("password");
    private static By countryList = By.id("countryList");
    private static By joinNowButton = By.id("form");
    private static By titleDropDown = By.id("title");
    private static By firstName = By.id("forename");
    private static By dobDay = By.id("dobDay");
    private static By dobMonth = By.id("dobMonth");
    private static By dobYear = By.id("dobYear");
    private static By mandatoryFieldText = By.cssSelector("label[class='error']");

    private final String pageTitle = "Join now to Play Online Casino Games - Free or Cash | MoneyGaming.com";


    public JoinNowPage(WebDriver driver) throws IOException {
        super(driver);
    }


    public void waitUntilLoaded() {
        log.debug("Waiting for Join Now page to be loaded");
       ;
        new WebDriverWait(driver, MAX_PAGE_LOADING_TIME).pollingEvery(500, TimeUnit.MILLISECONDS).until(ExpectedConditions.and
                (ExpectedConditions.visibilityOf(driver.findElement(regestrationContent)),
                        (ExpectedConditions.visibilityOf(driver.findElement(regestrationJourneyImage))),
                        (ExpectedConditions.titleIs(pageTitle)),
                        (ExpectedConditions.urlContains("sign-up.shtml"))));
    }

    public void clickJoinNowButton() throws ButtonNotAvailableException {
        try {
            this.scrollTo(joinNowButton);
            driver.findElement(joinNowButton).click();
        } catch (NoSuchElementException ex) {
            throw new ButtonNotAvailableException("Join Now Button in Sign up Page");
        }
    }

    public boolean verifyJoiNowHasAllFieldValues() {
        List<WebElement> signUpHeaders = driver.findElements(signUpFormFields);
        String legendText = "";
        boolean isFalse = false;
        int count = 0;
        List<String> actualValues = new ArrayList<String>();
        List<String> aboutYou = Arrays.asList("Title: *", "First Name: *", "Surname: *", "Date of Birth: *", "Email Address: *", "Telephone: *",
                "Mobile: *");
        List<String> address = Arrays.asList("Address Line 1: *", "Address 2", "City: *", "County: *", "Postcode: *", "Country *");
        List<String> userDetails = Arrays.asList("Choose Username *", "Choose Password *", "Re-type Password *", "Security question 1: *", "Answer: *",
                "Security question 2: *", "Answer: *");
        for (WebElement signUpHeader : signUpHeaders) {
            legendText = signUpHeader.findElement(By.cssSelector(signUpFormFieldsLegend)).getText();

            log.debug("Verifying sign up page has all the field labels under " + legendText);
            if (legendText.equalsIgnoreCase("About you")) {
                actualValues = valuesofLabel(signUpHeader);
                isFalse = CommonUtils.validateTwoStringList(actualValues, aboutYou, "field labels under " + legendText);

            } else if (legendText.equalsIgnoreCase("Address details")) {
                actualValues = valuesofLabel(signUpHeader);
                isFalse = CommonUtils.validateTwoStringList(actualValues, address, "field labels under " + legendText);
            } else {
                actualValues = valuesofLabel(signUpHeader);
                isFalse = CommonUtils.validateTwoStringList(actualValues, userDetails, "field labels under " + legendText);
            }

            if (isFalse) {
                count++;
            }
        }
        if (count == 3) {
            isFalse = true;
            log.debug("Sign up page has all the field " + isFalse);
        } else {
            log.debug("Sign up page has all the field " + isFalse);
            isFalse = false;
        }

        return isFalse;
    }

    private List<String> valuesofLabel(WebElement element) {
        List<WebElement> labelNames = element.findElements(By.cssSelector("label"));
        List<String> names = new ArrayList<String>();
        for (WebElement labelName : labelNames) {
            String x = labelName.getText();
            names.add(x);
        }
        return names;
    }

    public void enterSignUpFieldValues(Map<String, String> fields) throws OptionNotAvailableException, FieldNotAvailableException {
        String value = "";
        try {
            for (String filed : fields.keySet()) {
                value = fields.get(filed);
                log.debug("Entering " + value + " in the " + filed);
                switch (filed) {
                    case "Title: *":
                        this.selectDropDownOption(titleDropDown, value);
                        break;
                    case "First Name: *":
                        this.enterTextInField(firstName, value);
                        break;
                    case "SurName: *":
                        this.enterTextInField(surName, value);
                        break;
                    case "Date of Birth: *":
                        List<WebElement> dobGenerics = driver.findElements(dobGenericOptions);
                        String[] values = value.split("-");
                        for (WebElement dobGeneric : dobGenerics) {
                            String attributeName = dobGeneric.getAttribute("name");
                            if (attributeName.contains("dobDay")) {
                                this.selectDropDownOption(dobDay, values[0]);
                            } else if (attributeName.contains("dobMonth")) {
                                this.selectDropDownOption(dobMonth, values[1]);
                            } else {
                                this.selectDropDownOption(dobYear, values[2]);
                            }
                        }
                        break;
                    case "Email Address: *":
                        this.enterTextInField(emailAddress, value);
                        break;
                    case "Telephone: *":
                        this.enterTextInField(telephone, value);
                        break;
                    case "Mobile: *":
                        this.enterTextInField(mobile, value);
                        break;
                    case "Address Line 1: *":
                        this.enterTextInField(addressLineOne, value);
                        break;
                    case "City: *":
                        this.enterTextInField(addressCity, value);
                        break;
                    case "County: *":
                        this.enterTextInField(county, value);
                        break;
                    case "Postcode: *":
                        this.enterTextInField(postCode, value);
                        break;
                    case "Country *":
                        this.selectDropDownOption(countryList, value);
                        break;
                    case "Choose Username *":
                        this.enterTextInField(chooseUsername, value);
                        break;
                    case "Choose Password *":
                        this.enterTextInField(password, value);
                        break;
                    case "Re-type Password *":
                        this.enterTextInField(passwordConfirm, value);
                        break;
                    case "Security question 1: *":
                        this.selectDropDownOption(securityQuestionOne, value);
                        break;
                    case "Security question 2: *":
                        this.selectDropDownOption(securityQuestionTwo, value);
                        break;
                    case "Answer: *":
                        this.enterTextInField(securityQuestionOneAnswer, value);
                        break;
                    case "Answer two: *":
                        this.enterTextInField(securityQuestionTwoAnswer, value);
                        break;
                    case "Currency: *":
                        this.selectDropDownOption(currencyList, value);
                        break;
                }
            }
        } catch (OptionNotAvailableException | FieldNotAvailableException e) {
            throw new OptionNotAvailableException(value);
        }
    }

    public WebElement checkORUncheckBox(String checkOrUncheck, String checkBoxName) throws InterruptedException {
        String value = checkBoxName.equalsIgnoreCase("terms and conditions") ? "terms" :
                checkBoxName.equalsIgnoreCase("SMS Notifications") ? "marketingSms" : "marketingPhone";
        WebElement element = driver.findElement(By.cssSelector("input[name='map(" + value + ")']"));
        if (checkOrUncheck.equals("accept")) {
            element.click();
            // Had to add thread sleep as there is no changes in dom events for it to wait for
            Thread.sleep(1000);
            log.debug(checkBoxName + " is Selected");
        } else {
            element.click();
        }

        return element;
    }

    public boolean verifyMandatoryTextAppearsUnderFields(String mandatoryText, String fieldName) {
        List<WebElement> fields = driver.findElements(mandatoryFieldText);
        boolean isMandatoryTextDisplayed = false;
        for (WebElement field : fields) {
            String attributeValue = field.getAttribute("for");
            if (attributeValue.equalsIgnoreCase(fieldName)) {
                isMandatoryTextDisplayed = field.getText().equalsIgnoreCase(mandatoryText);
                log.debug(attributeValue + " has mandatory text i.e. " + mandatoryText);
                break;
            }
        }
        return isMandatoryTextDisplayed;

    }

    public boolean verifyTermAndConditionsIsSelected() throws InterruptedException {
        WebElement element = driver.findElement(termsAndConditions);
        boolean isSelected = this.isCheckBoxSelected(element);
        log.debug("Terms & Conditions are selected " + isSelected);
        return isSelected;
    }

}
