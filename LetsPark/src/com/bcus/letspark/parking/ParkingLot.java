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
    Map<String, Car> carParkedMap = null;

    public  ParkingLot() {
        carParkedMap = new HashMap<>();
    }
    public boolean parkMyCar(Car car) throws Exception {
        if(carParkedMap.containsValue(car))
            throw new Exception(SHOULD_NOT_PARK_SAME_CAR_TWICE);

        carParkedMap.put(car.getVehicleIdentificationNumber(), car);
        return  true;
    }

    public Car getMyCar(String vehicleIdentificationNumber) throws Exception {
        if(!carParkedMap.containsKey(vehicleIdentificationNumber)) {
            throw new Exception(CAR_NOT_PARKED_IN_PARKING_LOT);
        }
        Car carToBeReturned = carParkedMap.get(vehicleIdentificationNumber);
        carParkedMap.remove(vehicleIdentificationNumber);
        return carToBeReturned;
    }
}
