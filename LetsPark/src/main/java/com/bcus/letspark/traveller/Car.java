package com.bcus.letspark.traveller;


public class Car {


    private final String vehicleIdentificationNumber;
    private final CarSize carSize;
    private  final String color;



    public Car(String color, CarSize carSize, String vehicleIdentificationNumber) {
        this.color = color;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.carSize = carSize;

    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public CarSize getSize() {
        return carSize;
    }

    public String getColor() {
        return color;
    }
}
