package com.bcus.letspark.parking;

import traveller.Car;

import java.util.*;


public class ParkingLot extends Observable {

    static final String SHOULD_NOT_PARK_SAME_CAR_TWICE = "Car is already parked in the parking lot." ;
    public static final String CAR_NOT_PARKED_IN_PARKING_LOT = "Car not parked in the parking lot";
    public static final String PARKING_LOT_IS_FULL = "Parking lot is full";
    private Map<String, Car> carParkedMap = null;
    boolean notifyForAvailable = false;

    ArrayList<Observer> owners =  new ArrayList<>();
    ArrayList<Observer> fbiAgents =  new ArrayList<>();
    private int parkingSize = 0;
    private boolean isFull;
    private Integer size;

    public  ParkingLot(int parkingSize) {
        carParkedMap = new HashMap<>();
        this.parkingSize = parkingSize;

    }


    public synchronized boolean parkCar(Car car) throws Exception {
        if(isFull()) {
            throw new Exception(PARKING_LOT_IS_FULL);
        }
        if(carParkedMap.containsValue(car))
            throw new Exception(SHOULD_NOT_PARK_SAME_CAR_TWICE);
        carParkedMap.put(car.getVehicleIdentificationNumber(), car);
        notifyParkingLotObservers();

        return  true;
    }

    public synchronized Car getCarFromParking(String vehicleIdentificationNumber) throws Exception {
        if(!carParkedMap.containsKey(vehicleIdentificationNumber)) {
            throw new Exception(CAR_NOT_PARKED_IN_PARKING_LOT);
        }
        Car carToBeReturned = carParkedMap.get(vehicleIdentificationNumber);

        if(isFull())
        {
            notifyForAvailable = true;
            notifyParkingLotObservers();
            notifyForAvailable = false;
        }
        carParkedMap.remove(vehicleIdentificationNumber);
        return carToBeReturned;
    }

    private boolean isFull() {
        return (parkingSize - carParkedMap.size())== 0;
    }

    public boolean verify80PercentFull() {
        double percentFull = calcualtePercentageFull();
        return percentFull == 80;
    }

    public void notifyParkingLotObservers() {
        if(isFull())
        {
            notifyOwners("PARKING_FULL");
        }
        else if(notifyForAvailable)
        {
            notifyOwners("PARKING_AVAILABLE");
        }

        if(verify80PercentFull())
        {
            for(Observer observer : fbiAgents)
            {
                observer.update(this, "PARKING_EIGHTY_PERCENT_FULL");
            }
        }

    }

    private void notifyOwners(String message)
    {
        for(Observer parkingLotOwner : owners)
        {
            parkingLotOwner.update(this, message);
        }
    }

    @Override
    public synchronized void addObserver(Observer observer) {

        if(observer instanceof ParkingLotOwner){
            owners.add(observer);
            return;
        }
        fbiAgents.add(observer);
    }



    private double calcualtePercentageFull(){
        return (carParkedMap.size() * 100.0 )/parkingSize;
    }
}
