package com.moneygaming_gameaccount.exceptions;

public class OptionNotAvailableException extends Exception{
    public OptionNotAvailableException(String optionName){
        super("Option not available "+ optionName);
    }
}
