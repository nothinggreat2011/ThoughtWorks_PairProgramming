package com.bcus.letspark.exceptions;

/**
 * Created by Jerry on 24-04-2015.
 */
public class CarNotParkedException extends RuntimeException {
    public CarNotParkedException(String message) {
        super(message);
    }
}
