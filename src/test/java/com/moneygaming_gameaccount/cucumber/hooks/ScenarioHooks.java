package com.moneygaming_gameaccount.cucumber.hooks;

import com.moneygaming_gameaccount.selenium.webdriver.BrowserDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class ScenarioHooks {
    private static WebDriver driver;

    static {
        try {
            driver = BrowserDriver.getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScenarioHooks() throws IOException {}

    @Before
    public void before(Scenario scenario) {
        System.out.println(scenario.getName());
    }

    @After
    public void afterHooks(Scenario scenario) throws IOException {
        BrowserDriver.destroyDriver();
    }

/*    private static void takeScreenshot(String scenarioName) throws IOException {

        String localDir = System.getProperty("user.dir");

        File screenShot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = timeFormat.format(new java.util.Date(System.currentTimeMillis()));

        String name = timestamp + " - " + scenarioName + ".png";

        File destination = new File(localDir+ "screenshots", name);
        FileUtils.moveFile(screenShot, destination);

    }*/
}
