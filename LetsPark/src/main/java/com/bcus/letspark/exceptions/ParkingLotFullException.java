package com.bcus.letspark.exceptions;

/**
 * Created by Jerry on 24-04-2015.
 */
public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}
