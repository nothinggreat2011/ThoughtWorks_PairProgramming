package com.bcus.letspark.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sawai on 4/21/2015.
 */
public class ParkingLot {

    List<Car> carParkedList = null;

    public  ParkingLot() {
        carParkedList = new ArrayList<>();
    }
    public Integer parkMyCar(Car car) throws Exception {
        if(carParkedList.contains(car))
            throw new Exception("Car is already parked in the parking lot.");

        carParkedList.add(car);
        return  5;
    }
}
