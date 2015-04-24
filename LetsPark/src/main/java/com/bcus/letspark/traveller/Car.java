package com.bcus.letspark.traveller;


public class Car {

    private final String vehicleIdentificationNumber;
    private final CarSize carSize;



    public Car(CarSize carSize, String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.carSize = carSize;

    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public CarSize getSize() {
        return carSize;
    }
}
