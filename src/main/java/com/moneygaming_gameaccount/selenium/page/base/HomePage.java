package com.moneygaming_gameaccount.selenium.page.base;

import com.moneygaming_gameaccount.exceptions.ButtonNotAvailableException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class HomePage extends BasePage {
    private static Logger log = Logger.getLogger(HomePage.class);

    private By joinNowButtonLink =  By.cssSelector("a[href='/sign-up.shtml']");
    private By nivoSliderHomePage = By.cssSelector("div[id='slider']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean validateJoinNowButtonIsDisplayed(){
        log.debug("Checking if Join now button is displayed on home page");
        return driver.findElement(joinNowButtonLink).isDisplayed();
    }

    public boolean validateUserIsOnHomePage(){
        boolean isUserOnHomepage =  driver.findElement(nivoSliderHomePage).isDisplayed() &&
                this.getPageUrl().contains("https://moneygaming.qa.gameaccount.com/") &&
                this.getPageTitle().equalsIgnoreCase("Play Online Casino Games Now | MoneyGaming.com");
        log.debug("User is on homepage " +  isUserOnHomepage);
        return  isUserOnHomepage;
    }

    public JoinNowPage clickJoinNowButton() throws ButtonNotAvailableException, IOException {
        try {
            if(validateJoinNowButtonIsDisplayed()){
                log.debug("Join Now button is displayed, clicking on it");
                driver.findElement(joinNowButtonLink).click();
            }
        } catch (NoSuchElementException ex){
            throw new ButtonNotAvailableException("Join now");
        }
        JoinNowPage joinNow = new JoinNowPage(driver);
        joinNow.waitUntilLoaded();

        return joinNow;
    }

}
