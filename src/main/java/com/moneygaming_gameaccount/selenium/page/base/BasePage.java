package com.moneygaming_gameaccount.selenium.page.base;

import com.moneygaming_gameaccount.exceptions.FieldNotAvailableException;
import com.moneygaming_gameaccount.exceptions.OptionNotAvailableException;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class BasePage {

    protected final static int MAX_PAGE_LOADING_TIME = 10;
    protected WebDriver driver;
    private static Logger log = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    private WebElement createElement(By element) {
        return driver.findElement(element);
    }

    protected WebElement getLinkByText(String text) {
        try {
            return driver.findElement(By.linkText(text));
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    protected List<String> getDropDownOptions(WebElement webElement) {
        List<String> arrayList = new ArrayList<String>();
        try {
            Select select = new Select(webElement);
            for (WebElement option : select.getOptions()) {
                arrayList.add(option.getText());
            }
            return arrayList;
        } catch (NoSuchElementException ex) {
            return arrayList;
        }
    }

    protected String getDropDownOption(WebElement webElement) {
        return new Select(webElement).getFirstSelectedOption().getText();
    }

    protected void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", createElement(element));
    }

    protected void selectDropDownOption(By locator, String valueToSelect) throws OptionNotAvailableException {
        WebElement selectOption = driver.findElement(locator);
        Select select = new Select(selectOption);
        select.selectByValue(valueToSelect);
    }

    protected void enterTextInField(By locator, String valueToEnter) throws FieldNotAvailableException {
        WebElement webElement = driver.findElement(locator);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(valueToEnter);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isCheckBoxSelected(WebElement checkboxName) throws InterruptedException {
        Thread.sleep(1000);
        return checkboxName.isSelected();
    };

    public void waitUntilLoaded() {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}