package com.moneygaming_gameaccount.selenium.webdriver;

import com.moneygaming_gameaccount.config.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

public class BrowserDriver {
    private static Map<Long, WebDriver> driverCache = new Hashtable<>();
    private static Logger log = Logger.getLogger(BrowserDriver.class);
    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        return getDriver(Thread.currentThread().getId());
    }

    public static WebDriver getDriver(long tag) throws IOException {
        WebDriver driver = driverCache.get(tag);
        if (driver == null) {
            return createDriver();
        }
        return driver;
    }

    public static void destroyDriver() {
        log.debug("Destroying driver for tag '" + Thread.currentThread().getId() + "'");
        WebDriver driver = driverCache.get(Thread.currentThread().getId());
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver createDriver() throws MalformedURLException {

        String browserName = Config.getString(Config.ParamKey.BROWSER_NAME);
        String seleniumHub = Config.getString(Config.ParamKey.SELENIUM_HUB);

        Capabilities caps;
        if ("chrome".equals(browserName)) {
            caps = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            caps = caps.merge(options);
        } else if ("firefox".equals(browserName)) {
            caps = DesiredCapabilities.firefox();
            FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions options = new FirefoxOptions().setProfile(profile);
            caps = caps.merge(options);
        } else {
            throw new RuntimeException("Unknown browser: " + browserName);
        }

        log.debug("Starting selenium server local host - " + seleniumHub);
        driver = new RemoteWebDriver(new URL(seleniumHub), caps);
        driver.navigate().to(Config.getString(Config.ParamKey.BASE_URL));
        log.debug("Instantiating browser " + browserName);
        log.debug("Navigating to url " + Config.getString(Config.ParamKey.BASE_URL));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driverCache.put(Thread.currentThread().getId(), driver);

        return driver;
    }

}
