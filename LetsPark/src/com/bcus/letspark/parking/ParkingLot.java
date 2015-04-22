package com.bcus.letspark.parking;

import traveller.Car;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public class ParkingLot extends Observable {

    static final String SHOULD_NOT_PARK_SAME_CAR_TWICE = "Car is already parked in the parking lot." ;
    public static final String CAR_NOT_PARKED_IN_PARKING_LOT = "Car not parked in the parking lot";
    public static final String PARKING_LOT_IS_FULL = "Parking lot is full";
    private Map<String, Car> carParkedMap = null;
    private int availableParkingCounter = 0;
    private boolean isFull;

    public  ParkingLot(int parkingSize) {
        carParkedMap = new HashMap<>();
        availableParkingCounter = parkingSize;
    }


    public synchronized boolean parkCar(Car car) throws Exception {
        if(isFull()) {
            throw new Exception(PARKING_LOT_IS_FULL);
        }
        if(carParkedMap.containsValue(car))
            throw new Exception(SHOULD_NOT_PARK_SAME_CAR_TWICE);
        carParkedMap.put(car.getVehicleIdentificationNumber(), car);
        availableParkingCounter--;
        if(isFull())
        {
            setChanged();
            notifyObservers("PARKING_FULL");
        }

        return  true;
    }

    public synchronized Car getCarFromParking(String vehicleIdentificationNumber) throws Exception {
        if(!carParkedMap.containsKey(vehicleIdentificationNumber)) {
            throw new Exception(CAR_NOT_PARKED_IN_PARKING_LOT);
        }
        Car carToBeReturned = carParkedMap.get(vehicleIdentificationNumber);
        carParkedMap.remove(vehicleIdentificationNumber);
        availableParkingCounter++;
        if(!isFull())
        {
            if(availableParkingCounter == 1)
            {
                setChanged();
                notifyObservers("PARKING_AVAILABLE");

            }
        }

        return carToBeReturned;
    }

    private boolean isFull() {
        return availableParkingCounter == 0;
    }

}
