package com.moneygaming_gameaccount.utils;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class CommonUtils {

    private static  Logger log = Logger.getLogger(CommonUtils.class);


    public static boolean validateTwoStringList(List<String> actualValues, List<String> expectedValue, String userText){
            log.debug("Comparing actual vs expected " + userText);
//            log.debug("Actual Values --> " + actualValues);
//            log.debug("Expected Values --> " + expectedValue);
            return actualValues.equals(expectedValue);
    }
}
