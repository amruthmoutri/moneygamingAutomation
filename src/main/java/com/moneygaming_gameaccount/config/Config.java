package com.moneygaming_gameaccount.config;

import com.moneygaming_gameaccount.selenium.webdriver.BrowserDriver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

    private final static String PROP_LOCATION = "/src/test/resources//config/";
    private static String PROP_FILE_DEVTEST = "config_devtest.properties";;
    private static BrowserDriver driver;


    public enum ParamKey {
        SELENIUM_HUB("seleniumHub"),
        BROWSER_NAME("browserName"),
        BASE_URL("baseURL");

        String paramKeyValue;

        ParamKey(String paramKeyValue){
            this.paramKeyValue = paramKeyValue;
        }

        public String getValue(){
            return paramKeyValue;
        }
    }


    public static String getString(ParamKey key) {
        try{
            String localDir = System.getProperty("user.dir");
            String value = key.getValue();
            Properties properties = new Properties();
            FileReader fileReader = new FileReader(localDir + PROP_LOCATION + PROP_FILE_DEVTEST);
            properties.load(fileReader);
            return properties.getProperty(value);
        } catch (IOException ex){
            return "";
        }
    }


}
