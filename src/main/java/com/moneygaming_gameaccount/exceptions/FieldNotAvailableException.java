package com.moneygaming_gameaccount.exceptions;

public class FieldNotAvailableException extends Exception {
    FieldNotAvailableException(String fieldName){
        super("Filed not available to enter text " + fieldName);
    }
}
