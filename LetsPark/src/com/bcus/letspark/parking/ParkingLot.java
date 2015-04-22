package com.bcus.letspark.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sawai on 4/21/2015.
 */
public class ParkingLot {

    protected static final String SHOULD_NOT_PARK_SAME_CAR_TWICE = "Car is already parked in the parking lot." ;
    public static final String CAR_NOT_PARKED_IN_PARKING_LOT = "Car not parked in the parking lot";
    public static final String PARKING_LOT_IS_FULL = "Parking lot is full";
    Map<String, Car> carParkedMap = null;
    private int availableParkingCounter = 0;

    public  ParkingLot(int parkingSize) {
        carParkedMap = new HashMap<>();
        availableParkingCounter = parkingSize;
    }


    public boolean parkMyCar(Car car) throws Exception {
        if(availableParkingCounter == 0) {
            throw new Exception(PARKING_LOT_IS_FULL);
        }
        if(carParkedMap.containsValue(car))
            throw new Exception(SHOULD_NOT_PARK_SAME_CAR_TWICE);
        carParkedMap.put(car.getVehicleIdentificationNumber(), car);
        availableParkingCounter--;
        return  true;
    }

    public Car getMyCar(String vehicleIdentificationNumber) throws Exception {
        if(!carParkedMap.containsKey(vehicleIdentificationNumber)) {
            throw new Exception(CAR_NOT_PARKED_IN_PARKING_LOT);
        }
        Car carToBeReturned = carParkedMap.get(vehicleIdentificationNumber);
        carParkedMap.remove(vehicleIdentificationNumber);
        availableParkingCounter++;
        return carToBeReturned;
    }

    private boolean isFull() {
        return availableParkingCounter == 0;
    }
}
