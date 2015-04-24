package com.bcus.letspark.parking;

import com.bcus.letspark.traveller.Car;

import java.util.*;


public class ParkingLot extends Observable {

    static final String SHOULD_NOT_PARK_SAME_CAR_TWICE = "Car is already parked in the parking lot." ;
    public static final String CAR_NOT_PARKED_IN_PARKING_LOT = "Car not parked in the parking lot";
    public static final String PARKING_LOT_IS_FULL = "Parking lot is full";
    private Map<String, Car> carParkedMap = null;

    ArrayList<Observer> owners =  new ArrayList<>();
    ArrayList<Observer> fbiAgents =  new ArrayList<>();
    private int parkingSize = 0;
    private boolean isFull;
    private String parkingLotId;
    public  ParkingLot(String parkingLotId, int parkingSize) {
        this.parkingLotId = parkingLotId;
        carParkedMap = new HashMap<>();
        this.parkingSize = parkingSize;

    }


    public synchronized Ticket parkCar(Car car) throws Exception {
        if(isFull()) {
            throw new Exception(PARKING_LOT_IS_FULL);
        }
        if(carParkedMap.containsValue(car))
            throw new Exception(SHOULD_NOT_PARK_SAME_CAR_TWICE);
        carParkedMap.put(car.getVehicleIdentificationNumber(), car);
        notifyParkingLotObservers();
        Ticket ticket = new Ticket(this.parkingLotId,car.getVehicleIdentificationNumber());
        return  ticket;
    }

    public synchronized Car getCarFromParking(String vehicleIdentificationNumber) throws Exception {
        if(!carParkedMap.containsKey(vehicleIdentificationNumber)) {
            notifyFBIAgent("CAR_NOT_FOUND_IN_PARKING_LOT");
            throw new Exception(CAR_NOT_PARKED_IN_PARKING_LOT);
        }
        Car carToBeReturned = carParkedMap.get(vehicleIdentificationNumber);

        if(isFull())
        {
            notifyOwners("PARKING_AVAILABLE");
        }
        carParkedMap.remove(vehicleIdentificationNumber);
        notifyParkingLotObservers();
        return carToBeReturned;
    }

    public boolean isFull() {
        return (parkingSize - carParkedMap.size())== 0;
    }

    private boolean verify80PercentFull() {
        double percentFull = calculatePercentageFull();
        return percentFull == 80;
    }

    private void notifyParkingLotObservers() {
        if(isFull())
        {
            notifyOwners("PARKING_FULL");
        }

        if(verify80PercentFull())
        {
            notifyFBIAgent("PARKING_EIGHTY_PERCENT_FULL");
        }

    }

    private void notifyFBIAgent(String message)
    {
        for(Observer observer : fbiAgents)
        {
            observer.update(this, message);
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



    private double calculatePercentageFull(){
        return (carParkedMap.size() * 100.0 )/parkingSize;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }


    public Integer getParkingSize() {
        return parkingSize;
    }
}
