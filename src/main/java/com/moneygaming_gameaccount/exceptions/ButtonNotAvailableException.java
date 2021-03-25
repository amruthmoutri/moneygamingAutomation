package com.moneygaming_gameaccount.exceptions;

public class ButtonNotAvailableException extends Exception {

    public ButtonNotAvailableException(String buttonName){
        super("Button not Available " + buttonName);
    }
}
