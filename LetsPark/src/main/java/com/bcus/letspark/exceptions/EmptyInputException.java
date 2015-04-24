package com.bcus.letspark.exceptions;

/**
 * Created by Jerry on 24-04-2015.
 */
public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
