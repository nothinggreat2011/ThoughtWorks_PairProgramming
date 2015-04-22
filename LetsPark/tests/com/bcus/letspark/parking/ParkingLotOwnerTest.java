package com.bcus.letspark.parking;


import org.junit.Test;
import traveller.Car;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotOwnerTest {

    @Test
    public void notifyOwnerWhenParkingIsFull() throws Exception {
        int parkingLotSize = 2;
        ParkingLotOwner parkingLotOwner = new  ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addObserver(parkingLotOwner);
        Car carOne = new Car("One");
        Car carTwo = new Car("two");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);

        boolean isParkingEmpty = parkingLotOwner.isParkingFull();
        assertThat(isParkingEmpty,  is(true));

    }

    @Test
    public void notifyOwnerWhenParkingIsEmpty() throws Exception {
        int parkingLotSize = 2;
        ParkingLotOwner parkingLotOwner = new  ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        parkingLot.addObserver(parkingLotOwner);

        Car carOne = new Car("One");
        Car carTwo = new Car("two");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);

        parkingLot.getCarFromParking(carOne.getVehicleIdentificationNumber());
        parkingLot.getCarFromParking(carTwo.getVehicleIdentificationNumber());

        boolean isParkingEmpty = parkingLotOwner.isParkingFull();
        assertThat(isParkingEmpty,  is(false));

    }

}
