package com.bcus.letspark.exceptions;

/**
 * Created by Jerry on 24-04-2015.
 */
public class ShouldNotParkSameCarException extends  RuntimeException {
    public ShouldNotParkSameCarException(String message) {
        super(message);
    }
}
